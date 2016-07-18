package com.nextyu.study.proxy.interceptor;

/**
 * @author nextyu
 * @version 1.0
 */
public class SecondInterceptor implements MyInterceptor {
    @Override
    public void interceptor(ActionInvocation invocation) {
        System.out.println(2);

        invocation.invoke();

        System.out.println(-2);
    }
}
