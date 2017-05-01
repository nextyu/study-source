package com.nextyu.study.proxy.jdk.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 2017-05-01 下午3:00
 *
 * @author nextyu
 */
public class MyHandlerV2 implements InvocationHandler {

    /**
     * 目标对象.
     */
    private Object targetObject;

    public MyHandlerV2(Object targetObject) {
        this.targetObject = targetObject;
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
        System.out.println("--------------  before invoke method " + method.getName() + "  --------------");

        System.out.println("--------------  invoke method ：" + method.getName() + "  --------------");

        //目标方法执行结果
        Object result = null;
        try {

            System.out.println("-----参数-----");
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    System.out.println(arg);
                }
            }
            System.out.println("-----参数-----");
            // 调用目标对象的方法
            result = method.invoke(targetObject, args);
            System.out.println("执行结果：" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------------  after invoke method " + method.getName() + "  --------------");

        return result;
    }
}
