package com.nextyu.study.proxy.interceptor;

/**
 * @author nextyu
 * @version 1.0
 */
public class FirstInterceptor implements MyInterceptor {
    @Override
    public void interceptor(ActionInvocation invocation) {
        System.out.println(1);

        invocation.invoke();

        System.out.println(-1);
    }
}
