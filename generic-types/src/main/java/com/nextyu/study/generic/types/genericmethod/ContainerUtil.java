package com.nextyu.study.generic.types.genericmethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * utilities to simplify generic container creation by using type argument inference.
 *
 * @author zhouyu
 */
public class ContainerUtil {

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        Map<Integer, List<String>> map = ContainerUtil.map();
        List<Long> list = ContainerUtil.list();
    }

}
