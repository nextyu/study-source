package com.nextyu.study.proxy.test;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.KeyFactory;
import org.junit.Test;

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
        Object key1 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key2 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key3 = f.newInstance(20, new char[]{ 'a', '_' }, "hello");
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(key3);
        System.out.println(key1.equals(key2));
        System.out.println(key2.equals(key3));
    }
}
