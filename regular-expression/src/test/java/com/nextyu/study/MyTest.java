package com.nextyu.study;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2017-10-18 10:12
 *
 * @author nextyu
 */
public class MyTest {

    @Test
    public void test1() {
        String expression = "HelloWorld";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(expression);

        // 现在创建 Matcher 对象
        Matcher matcher = pattern.matcher("HelloWorld");

        if (matcher.matches()) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }
    }

    @Test
    public void test2() {
        String expression = "http://www.abc.com/\\d+\\.html";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(expression);

        // 现在创建 Matcher 对象
        Matcher matcher = pattern.matcher("http://www.abc.com/1.html");

        if (matcher.matches()) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }
    }

    @Test
    public void test3() {
//        String expression = ".+";
        String expression = "\\.";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(expression);

        // 现在创建 Matcher 对象
        Matcher matcher = pattern.matcher("a");

        if (matcher.matches()) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }
    }

    @Test
    public void test4() {
        String expression = "http://www.abc.com/(\\d+)\\.html";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(expression);

        // 现在创建 Matcher 对象
        Matcher matcher = pattern.matcher("http://www.abc.com/1.html");

        if (matcher.matches()) {
            System.out.println("匹配");
            System.out.println("group0：" + matcher.group());
            System.out.println("group1：" + matcher.group(1));
        } else {
            System.out.println("不匹配");
        }
    }

    @Test
    public void test5() {
        String expression = "(\\d)\\D(\\d)\\D(\\d)\\D";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(expression);

        // 现在创建 Matcher 对象
        Matcher matcher = pattern.matcher("3室1厅2卫");

        if (matcher.matches()) {
            System.out.println("匹配");
            System.out.println("group0：" + matcher.group());
            System.out.println("group1：" + matcher.group(1));
            System.out.println("group2：" + matcher.group(2));
            System.out.println("group3：" + matcher.group(3));
        } else {
            System.out.println("不匹配");
        }
    }

}
