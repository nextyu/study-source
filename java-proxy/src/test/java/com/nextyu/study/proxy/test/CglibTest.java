package com.nextyu.study.proxy.test;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.KeyFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created on 2017-05-02 14:54
 *
 * @author nextyu
 */
public class CglibTest {
    private interface MyFactory {
        Object newInstance(int a, char[] b, String d);
    }

    @Test
    public void testKeyFactory() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\");
        MyFactory f = (MyFactory) KeyFactory.create(MyFactory.class);
        Object key1 = f.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key2 = f.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key3 = f.newInstance(20, new char[]{'a', '_'}, "hello");
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(key3);
        System.out.println(key1.equals(key2));
        System.out.println(key2.equals(key3));
    }

    @Test
    public void test111() {
        long start = System.currentTimeMillis();
        ArrayList list = new ArrayList();
        Object obj = new Object();
        for (int i = 0; i < 5000000; i++) {
            list.add(obj);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        LinkedList list1 = new LinkedList();
        Object obj1 = new Object();
        for (int i = 0; i < 5000000; i++) {
            list1.add(obj1);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        Object obj2 = new Object();
        for (int i = 0; i < 1000; i++) {
            list.add(0, obj2);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        Object obj3 = new Object();
        for (int i = 0; i < 1000; i++) {
            list1.add(obj1);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        list.remove(0);
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        list1.remove(250000);
        end = System.currentTimeMillis();
        System.out.println(end - start);


    }

}
