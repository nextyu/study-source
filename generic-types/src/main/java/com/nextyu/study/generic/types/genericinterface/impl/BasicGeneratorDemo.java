package com.nextyu.study.generic.types.genericinterface.impl;


import com.nextyu.study.generic.types.genericinterface.Generator;

/**
 * @author zhouyu
 */
public class BasicGeneratorDemo {

    public static void main(String[] args) {
        Generator<CountedObject> countedObjectGenerator = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(countedObjectGenerator.next());
        }

        BasicGenerator<Fibonacci> fibonacciBasicGenerator = new BasicGenerator<>(Fibonacci.class);
        Fibonacci fibonacci = fibonacciBasicGenerator.next();
    }
}
