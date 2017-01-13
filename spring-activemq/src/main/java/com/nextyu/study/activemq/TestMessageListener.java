package com.nextyu.study.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * 2017-01-13 下午1:44
 *
 * @author nextyu
 */
public class TestMessageListener implements MessageListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination testQueue;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
