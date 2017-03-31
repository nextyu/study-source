package com.nextyu.study.activemq.listener;

import com.nextyu.study.activemq.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * 2017-02-06 下午1:30
 *
 * @author nextyu
 */
public class ObjectMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                User user = (User) objectMessage.getObject();
                System.out.println(user);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-activemq-consumer-object.xml");
        context.registerShutdownHook();
        Thread.currentThread().join();
    }
}
