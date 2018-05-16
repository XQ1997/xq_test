package com.kaisheng.listener;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * 使用spring管理jms 队列的实现监听器
 * @author Administrator
 */
public class JmsListener3 implements SessionAwareMessageListener {

    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();

        if(1 == 1) {
            //   session.recover();
            throw new RuntimeException("故意抛出的异常:" + text);
        }

    }
}
