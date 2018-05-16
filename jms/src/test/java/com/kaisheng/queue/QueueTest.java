package com.kaisheng.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;
import java.util.Date;

public class QueueTest {

    @Test
    public void provided(){
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
           //while (true) {
               //1.创建连接工厂
               ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
               //2.创建连接并连接
               connection = connectionFactory.createConnection();
               connection.start();
               //3.创建Session true 使用事务  CLIENT_ACKNOWLEDGE 手动签收
               session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
               //4.创建消息目的地
               Destination destination = session.createQueue("hello-activemq");
               //5.创建生产者
               producer = session.createProducer(destination);
               //使用持久模式
               producer.setDeliveryMode(DeliveryMode.PERSISTENT);
               //发送消息
               TextMessage textMessage = session.createTextMessage("hello,activemq-3");
               producer.send(textMessage);
               //使用事务进行手动提交
               session.commit();
               Thread.sleep(3000);
         //  }
       }catch(Exception e){
           throw new RuntimeException("铺货异常");
       }finally {
            close(producer,connection,session);
       }
    }

    @Test
    public void consumer() throws IOException, JMSException {
        //1.创建连接工厂
       //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        //设置重试的参数
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置重试次数
        redeliveryPolicy.setMaximumRedeliveries(2);
        //设置初次重试延迟时间，单位毫秒
        redeliveryPolicy.setInitialRedeliveryDelay(5000);
        //设置每次重试延迟时间，单位毫秒
        redeliveryPolicy.setRedeliveryDelay(5000);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        //2.创建连接并连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.创建Session  false 不使用事务   CLIENT_ACKNOWLEDGE 手动签收
        final Session sessions = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //4.创建消息目的地
        Destination destination = sessions.createQueue("hello-activemq");
        //5. 创建消费者
        MessageConsumer consumer = sessions.createConsumer(destination);
        //6.获取消息

        consumer.setMessageListener(new MessageListener(){
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    if("hello,activemq-3".equals(textMessage.getText())){
                        throw new RuntimeException("重试机制");
                    }
                  /* if(1 == 1){
                       throw new RuntimeException("haha");
                   }*/
                    System.out.println(textMessage.getText());
                    textMessage.acknowledge();
                } catch (Exception e) {
                    e.printStackTrace();
                    //触发重试机制
                    try {
                        sessions.recover();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        close(consumer,connection,sessions);
    }

    private void close(MessageProducer producer,Connection connection,Session session){
        if(producer != null){
            try {
                producer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }finally {
                if(connection != null){
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }finally {
                        if(session != null){
                            try {
                                session.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    private void close(MessageConsumer consumer,Connection connection,Session session){
        if(consumer != null){
            try {
                consumer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }finally {
                if(connection != null){
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }finally {
                        if(session != null){
                            try {
                                session.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
