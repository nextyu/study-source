package com.nextyu.study.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * created on 2016-07-18 10:02
 *
 * @author nextyu
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
