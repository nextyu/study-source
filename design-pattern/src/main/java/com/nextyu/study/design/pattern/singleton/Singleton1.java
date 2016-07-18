package com.nextyu.study.design.pattern.singleton;

/**
 * 单例设计模式：恶汉式.
 *
 * @author nextyu
 * @version 1.0
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    /**
     * 私有化默认构造方法，不能在该类外面实例化该类
     */
    private Singleton1() {
    }

    ;

    /**
     * 提供公有的静态方法，可以在外部调用该方法，返回该类的唯一实例
     *
     * @return
     */
    public static Singleton1 getInstance() {
        return instance;
    }

}
