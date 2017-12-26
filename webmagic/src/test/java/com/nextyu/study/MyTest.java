package com.nextyu.study;

/**
 * created on 2017-12-26 16:19
 *
 * @author nextyu
 */
public class MyTest {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable, "线程1");
        Thread thread2 = new Thread(myRunnable, "线程2");
        Thread thread3 = new Thread(myRunnable, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }


}
