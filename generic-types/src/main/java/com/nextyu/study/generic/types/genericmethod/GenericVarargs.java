package com.nextyu.study.generic.types.genericmethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 可变参数与泛型方法.
 *
 * @author zhouyu
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args) {
        List<T> list = new ArrayList<>();
        for (T arg : args) {
            list.add(arg);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> strings = makeList("a", "b");
        System.out.println(strings);
    }
}
