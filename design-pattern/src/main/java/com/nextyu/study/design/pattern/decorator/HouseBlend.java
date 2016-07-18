package com.nextyu.study.design.pattern.decorator;

/**
 * 被装饰者，具体实现.
 *
 * @author nextyu
 * @version 1.0
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
