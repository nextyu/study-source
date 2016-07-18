package com.nextyu.study.disruptor;

/**
 * created on 2016-07-18 10:01
 *
 * @author nextyu
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
