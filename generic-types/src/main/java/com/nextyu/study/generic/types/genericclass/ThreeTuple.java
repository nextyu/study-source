package com.nextyu.study.generic.types.genericclass;

/**
 * 元组.
 * 持有多个对象.
 * return 语句只允许返回单个对象，解决办法是创建一个对象，用它来持有想要返回的多个对象.
 *
 * @author zhouyu
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.third = c;
    }

    public static void main(String[] args) {
        ThreeTuple<String, Integer, Boolean> hello = new ThreeTuple<>("hello", 1, Boolean.TRUE);
        System.out.println(hello.first);
        System.out.println(hello.second);
        System.out.println(hello.third);
    }
}
