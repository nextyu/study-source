package com.nextyu.study.proxy.test;


import com.nextyu.study.proxy.cglib.dynamic.proxy.CglibProxy;
import com.nextyu.study.proxy.domain.User;
import com.nextyu.study.proxy.interceptor.ActionInvocation;
import com.nextyu.study.proxy.jdk.dynamic.proxy.MyHandler;
import com.nextyu.study.proxy.jdk.staic.proxy.UserServiceImplProxy;
import com.nextyu.study.proxy.service.OtherService;
import com.nextyu.study.proxy.service.UserService;
import com.nextyu.study.proxy.service.impl.UserAndOtherServiceImpl;
import com.nextyu.study.proxy.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author nextyu
 * @version 1.0
 */
public class ProxyTest {

    /**
     * jdk静态代理测试.
     */
    @Test
    public void testJDKStaticProxy() {
        UserService userService = new UserServiceImplProxy(new UserServiceImpl());
        userService.addUser(new User());
    }

    /**
     * jdk动态代理测试.
     */
    @Test
    public void testJDKDynamicProxy() throws Exception {
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 设置了这个没起作用,没有保存自动生成的.class文件

        // 此处的 UserService 不是原本的 UserService ,而是 UserService 的代理对象,是代理!!!
        UserService userService = (UserService) new MyHandler().newInstance(new UserAndOtherServiceImpl());
        System.out.println("代理对象 = " + userService.getClass().getName());

        // 传入的是 Jack 返回的却是 Rose
        User user = userService.addUser(new User(1, "Jack"));
        System.out.println(user);
        System.out.println("--------------------------------------------------");
        userService.delUser(1);
        System.out.println("--------------------------------------------------");
        OtherService otherService = (OtherService) new MyHandler().newInstance(new UserAndOtherServiceImpl());
        otherService.sayHi();


        // 创建并保存代理类.class文件
        createProxyClassFile("F:/" + userService.getClass().getSimpleName());
    }

    /**
     * 创建并保存代理类.class文件
     *
     * @param name 文件名
     */
    private void createProxyClassFile(String name) {

        byte[] data = ProxyGenerator.generateProxyClass(name, UserAndOtherServiceImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * cglib动态代理测试.
     */
    @Test
    public void testCglibDynamicProxy() {
        Enhancer enhancer = new Enhancer();

        // 设置被代理的类（目标类）
        enhancer.setSuperclass(UserServiceImpl.class);
        //  设置回调
        enhancer.setCallback(new CglibProxy());

        // 创建 代理 （动态扩展了UserServiceImpl类）
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();

        // 传入的是 Jack 返回的却是 Rose
        User user = userService.addUser(new User(1, "Jack"));
        System.out.println(user);
        System.out.println("--------------------------------------------------");
    }

    /**
     * 创建10000个代理对象，jdk动态代理和cglib动态代理耗时测试.
     * jdk动态代理效率更高.
     */
    @Test
    public void testJDKDynamicProxyCapability() {
        int num = 1000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            UserService userService = (UserService) new MyHandler().newInstance(new UserServiceImpl());
            User user = userService.addUser(new User(1, "Jack"));
        }
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println("耗时：" + result + "毫秒");
    }

    /**
     * 创建10000个代理对象，jdk动态代理和cglib动态代理耗时测试.
     * jdk动态代理效率更高.
     */
    @Test
    public void testCglibDynamicProxyCapability() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new CglibProxy());
        int num = 1000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            UserServiceImpl userService = (UserServiceImpl) enhancer.create();
            User user = userService.addUser(new User(1, "Jack"));
        }
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println("耗时：" + result + "毫秒");
    }

    /**
     * 模拟Struts2的拦截器测试.
     */
    @Test
    public void testInterceptor() {
        new ActionInvocation().invoke();
    }
}
