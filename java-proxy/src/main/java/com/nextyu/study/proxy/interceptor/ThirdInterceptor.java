package com.nextyu.study.proxy.interceptor;

/**
 * @author nextyu
 * @version 1.0
 */
public class ThirdInterceptor implements MyInterceptor {
    @Override
    public void interceptor(ActionInvocation invocation) {
        System.out.println(3);

        invocation.invoke();

        System.out.println(-3);
    }
}
