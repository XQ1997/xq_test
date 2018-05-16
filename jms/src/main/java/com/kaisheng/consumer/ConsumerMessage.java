package com.kaisheng.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class ConsumerMessage {

    @JmsListener(destination = "hello-spring-queue")
    public  void getmessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("-->" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "spring-topic",containerFactory = "jmsTopicListenerContainerFactory")
    public  void gettopic(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("-->" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
