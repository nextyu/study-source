package com.nextyu.study.activemq;

import org.joda.time.DateTime;
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
import java.util.concurrent.TimeUnit;

/**
 * 消息生产者
 * 2017-01-13 下午1:48
 *
 * @author nextyu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activemq-producer.xml")
public class MessageProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination testQueue;

    @Test
    public void addMessage() {
        jmsTemplate.send(testQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("我是文本消息-" + new DateTime().toString("yyyy-DD-mm HH:mm:ss"));
            }
        });
    }

    @Test
    public void addDelayMessage() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            String dateTime = new DateTime().toString("yyyy-DD-mm HH:mm:ss");

            Object message = "消息-" + dateTime;
            jmsTemplate.convertAndSend(testQueue, message, new ScheduleMessagePostProcessor(10L * 1000));//延时10秒
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
