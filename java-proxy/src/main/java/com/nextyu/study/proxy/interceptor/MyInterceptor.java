package com.nextyu.study.proxy.interceptor;

/**
 * 模拟Struts2的拦截器.
 *
 * @author nextyu
 * @version 1.0
 */
public interface MyInterceptor {
    void interceptor(ActionInvocation invocation);
}
