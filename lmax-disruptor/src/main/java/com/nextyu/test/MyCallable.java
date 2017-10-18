package com.nextyu.test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 2017-09-29 14:22
 *
 * @author nextyu
 */
public class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        // 真实的业务逻辑，其执行可能很慢
        TimeUnit.SECONDS.sleep(3);
        return "haha";
    }
}
