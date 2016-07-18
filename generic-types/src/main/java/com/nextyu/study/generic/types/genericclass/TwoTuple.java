package com.nextyu.study.generic.types.genericclass;

/**
 * 元组.
 * 持有多个对象.
 * return 语句只允许返回单个对象，解决办法是创建一个对象，用它来持有想要返回的多个对象.
 *
 * @author zhouyu
 */
public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> hello = new TwoTuple<>("hello", 2);
        System.out.println(hello.first);
        System.out.println(hello.second);
    }
}
