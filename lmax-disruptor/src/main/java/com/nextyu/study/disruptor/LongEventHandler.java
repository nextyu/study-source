package com.nextyu.study.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * consumer
 * <p/>
 * created on 2016-07-18 10:03
 *
 * @author nextyu
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        logger.debug("Event: {}", event);
    }
}
