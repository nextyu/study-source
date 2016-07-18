package com.nextyu.study.design.pattern.singleton;

import org.junit.Test;

/**
 * 测试.
 *
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();

        System.out.println(instance1 == instance2);
    }

}
