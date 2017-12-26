package com.nextyu.study;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created on 2017-12-26 16:20
 *
 * @author nextyu
 */
public class MyRunnable implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "-------------获得锁");

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "-------------释放锁");
            lock.unlock();
        }

    }
}
