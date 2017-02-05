package com.nextyu.study.activemq;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * 2017-01-13 下午1:44
 *
 * @author nextyu
 */
public class TestMessageListener2 implements MessageListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination testQueue;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String dateTime = new DateTime().toString("yyyy-DD-mm HH:mm:ss");
            System.out.println("线程名称-" + Thread.currentThread().getName() + "-消费者2--消费时间:" + dateTime + "----------内容:" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-activemq-consumer-2.xml");
        context.registerShutdownHook();
        Thread.currentThread().join();
    }
}
