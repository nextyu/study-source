package com.nextyu.study.proxy.cglib.dynamic.proxy;

import com.nextyu.study.proxy.domain.User;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author nextyu
 * @version 1.0
 */
public class CglibProxy implements MethodInterceptor {

    /**
     *
     * @param o 被增强了的对象（代理对象）
     * @param method 被拦截的方法
     * @param objects 参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用的方法：" + method.getName());

        //目标方法执行结果
        Object result = null;
        try {

            System.out.println("参数：");
            if (objects != null && objects.length > 0) {
                for (Object arg : objects) {
                    System.out.println(arg);
                }
            }
            // Note: 此处一定要使用methodProxy的invokeSuper方法来调用目标类的方法
            result = methodProxy.invokeSuper(o, objects);
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
