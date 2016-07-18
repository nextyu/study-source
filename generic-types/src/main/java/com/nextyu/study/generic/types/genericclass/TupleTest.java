package com.nextyu.study.generic.types.genericclass;

/**
 * @author zhouyu
 */
public class TupleTest {
    public static void main(String[] args) {
        TwoTuple<String, Integer> twoTuple = TupleUtil.tuple("哈哈", 1);
        System.out.println(twoTuple.first);
        System.out.println(twoTuple.second);
    }
}
