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
