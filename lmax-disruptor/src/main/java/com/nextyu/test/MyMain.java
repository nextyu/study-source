package com.nextyu.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 2017-09-29 14:23
 *
 * @author nextyu
 */
public class MyMain {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);

        // 做一些其他的任务

        // 再来获取数据
        System.out.println(futureTask.get());
    }
}
