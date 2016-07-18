package com.nextyu.study.design.pattern.singleton;

/**
 * 单例设计模式：懒汉式.
 *
 * @author nextyu
 * @version 1.0
 */
public class Singleton2 {

    private volatile static Singleton2 instance;

    /**
     * 私有化默认构造方法，不能在该类外面实例化该类.
     */
    private Singleton2() {
    }


    /**
     * double-checked locking.
     * 提供公有的静态方法，可以在外部调用该方法，返回该类的唯一实例.
     *
     * @return
     */
    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
