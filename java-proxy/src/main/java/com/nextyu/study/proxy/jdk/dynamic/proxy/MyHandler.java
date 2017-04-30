package com.nextyu.study.proxy.jdk.dynamic.proxy;


import com.nextyu.study.proxy.domain.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyHandler implements InvocationHandler {

    /**
     * 目标对象.
     */
    private Object targetObject;

    /**
     * 返回代理对象
     *
     * @param targetObject 目标对象(被代理的对象)
     * @return 代理对象
     */
    public Object newInstance(Object targetObject) {
        this.targetObject = targetObject;
        Class<?> targetObjectClass = targetObject.getClass();
        // 返回代理对象
        // newProxyInstance()方法有三个参数
        // 1. 类加载器（ClassLoader）,用来加载动态代理类
        // 2. 一个需要实现的接口的数组
        // 3. 一个InvocationHandler,把所有方法的调用都转到当前指定的InvocationHandler上
        return Proxy.newProxyInstance(targetObjectClass.getClassLoader(), targetObjectClass.getInterfaces(), this);
    }


    /**
     * 调用代理对象的任何方法都会转到这个方法上面
     *
     * @param proxy  代理对象
     * @param method 目标方法
     * @param args   目标方法的参数
     * @return 目标方法执行结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("调用的方法：" + method.getName());

        //目标方法执行结果
        Object result = null;
        try {

            System.out.println("参数：");
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    System.out.println(arg);
                }
            }

            result = method.invoke(targetObject, args);
            System.out.println("执行结果：" + result);
            if (result instanceof User) {
                System.out.println("修改执行结果：");
                User user = (User) result;
                user.setUsername("Rose");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
