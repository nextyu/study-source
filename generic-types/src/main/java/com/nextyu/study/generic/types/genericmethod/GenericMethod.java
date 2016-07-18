package com.nextyu.study.generic.types.genericmethod;

/**
 * 泛型方法.
 * 类型参数判断(type argument inference).
 *
 * @author zhouyu
 */
public class GenericMethod {

    public <T> void f(T x) {
        System.out.println(x.getClass().getSimpleName());
    }

    public <T, B> void m(T t, B b) {
        System.out.println(t.getClass().getSimpleName() + "--" + b.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.f("");
        genericMethod.f(1);
        genericMethod.f(1.0);

        genericMethod.m(1, "");
    }

}
