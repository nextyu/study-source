package com.nextyu.study.activemq;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.util.StringUtils;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * MQ延时投递处理器（注：ActiveMQ的activemq.xml配置文件中，要配置schedulerSupport="true"，否则不起作用）
 * 2017-02-05 下午5:14
 *
 * @author nextyu
 */
public class ScheduleMessagePostProcessor implements MessagePostProcessor {

    private Long delay = 0L;
    private String cron = null;

    public ScheduleMessagePostProcessor(Long delay) {
        this.delay = delay;
    }

    public ScheduleMessagePostProcessor(String cron) {
        this.cron = cron;
    }

    @Override
    public Message postProcessMessage(Message message) throws JMSException {
        if (delay > 0L) {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        }

        if (!StringUtils.isEmpty(cron)) {
            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, cron);
        }

        return message;
    }
}
