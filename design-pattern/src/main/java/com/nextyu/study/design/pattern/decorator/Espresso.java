package com.nextyu.study.design.pattern.decorator;

/**
 * 浓缩咖啡，被装饰者，具体实现.
 *
 * @author nextyu
 * @version 1.0
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso(浓缩咖啡)";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
