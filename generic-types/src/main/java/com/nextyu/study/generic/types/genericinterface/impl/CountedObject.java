package com.nextyu.study.generic.types.genericinterface.impl;

/**
 * @author zhouyu
 */
public class CountedObject {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return "CountedObject " + id;
    }
}
