package com.kaisheng.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;
import java.util.Date;

public class TopicTest {

    @Test
    public void provided() throws JMSException{
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2.创建连接并连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.创建Session 不使用事务  手动签收
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //4.创建Topic对象
        Topic topic = session.createTopic("hello-topic");
        //5.创建生产者
        MessageProducer producer = session.createProducer(topic);
        //6.发送消息
        TextMessage textMessage = session.createTextMessage("topic" + new Date().getTime());
        producer.send(textMessage);
        //7.释放资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumer() throws JMSException, IOException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        //2.创建连接并连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建Session 不使用事务 手动签收
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //4.创建目的地对象
        Topic topic = session.createTopic("hello-topic");
        //5.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //6.获取消息
        consumer.setMessageListener(new MessageListener(){
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    //System.out.println("textmessage" + textMessage);//输出的是对象
                    System.out.println("text" + textMessage.getText());//输出的是队列中的值
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //继续运行
        System.in.read();
        //7.关闭资源
        consumer.close();
        connection.close();
        session.close();
    }
}
