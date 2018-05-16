package com.kaisheng.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 使用spring管理jms 队列的实现监听器
 * @author Administrator
 */
public class JmsListener2 implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("2." + textMessage.getText());
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
