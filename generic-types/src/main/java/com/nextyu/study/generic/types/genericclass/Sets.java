package com.nextyu.study.generic.types.genericclass;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouyu
 */
public class Sets {

    /**
     * 并集
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 交集
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * 差集,subtract subSet from superSet
     */
    public static <T> Set<T> difference(Set<T> superSet, Set<T> subSet) {
        Set<T> result = new HashSet<>(superSet);
        result.removeAll(subSet);
        return result;
    }

    /**
     * reflexive everything not in the intersection
     */
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }


}
