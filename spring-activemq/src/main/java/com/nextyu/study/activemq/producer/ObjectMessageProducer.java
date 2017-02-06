package com.nextyu.study.activemq.producer;

import com.nextyu.study.activemq.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * 2017-02-06 下午1:33
 *
 * @author nextyu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activemq-producer-object.xml")
public class ObjectMessageProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination objectQueue;


    @Test
    public void addObjectMessage() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            User user = new User((long) i, "小明-" + i);
            jmsTemplate.convertAndSend(objectQueue, user);
        }

    }
}
