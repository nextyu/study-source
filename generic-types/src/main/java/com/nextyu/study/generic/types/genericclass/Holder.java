package com.nextyu.study.generic.types.genericclass;

/**
 * 泛型类.
 *
 * @author zhouyu
 */
public class Holder<T> {
    private T a;

    public Holder(T a) {
        this.a = a;
    }

    public void set(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    public static void main(String[] args) {
        Holder<String> stringHolder = new Holder<>("hello");
        stringHolder.set("hi");
        System.out.println(stringHolder.get());
    }
}
