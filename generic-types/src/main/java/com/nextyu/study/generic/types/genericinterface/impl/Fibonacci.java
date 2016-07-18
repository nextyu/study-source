package com.nextyu.study.generic.types.genericinterface.impl;


import com.nextyu.study.generic.types.genericinterface.Generator;

/**
 * 斐波拉契数列.
 *
 * @author zhouyu
 */
public class Fibonacci implements Generator<Integer> {

    private int count = 0;

    @Override
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2)
            return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.print(fibonacci.next() + " ");
        }
    }
}
