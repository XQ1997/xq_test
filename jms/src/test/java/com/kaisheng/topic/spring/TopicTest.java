package com.kaisheng.topic.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-topic.xml")
@ContextConfiguration(locations = "classpath:spring-topics.xml")
public class TopicTest {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;

    @Test
    public void sendMessage() throws IOException {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello,spring,topic3");
            }
        });
        System.in.read();
    }

    @Test
    public void receive() throws IOException {
        System.out.println("等待接收");
        System.in.read();
    }
}
