package com.nextyu.study.generic.types.genericinterface.impl;


import com.nextyu.study.generic.types.genericinterface.Generator;

/**
 * @author zhouyu
 */
public class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * produce a default generator given a type token.
     *
     * @param type Class
     * @return Generator
     */
    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }

}
