package com.nextyu.study.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * created on 2016-07-18 10:08
 *
 * @author nextyu
 */
public class LongEventProducer {
    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next(); // Grab the next sequence

        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor for the sequence
            event.setValue(bb.getLong(0)); // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
