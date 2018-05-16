package com.kaisheng.queue.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-queue.xml")
@ContextConfiguration(locations = "classpath:spring-queues.xml")
public class QueueTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessage() throws IOException {
        //不指定 使用默认的目的地名，指定使用该目的地名
        jmsTemplate.send("hello-spring-queue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello,spring,queue22");
            }
        });
    }
    @Test
    public void receive() throws IOException {
        System.out.println("测试接收");
        System.in.read();
    }
}
