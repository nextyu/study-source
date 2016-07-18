package com.nextyu.study.design.pattern.decorator;

/**
 * 调料，豆浆，装饰者，具体实现.
 *
 * @author nextyu
 * @version 1.0
 */
public class Soy extends CondimentDecorator {

    /**
     * 被装饰者.
     */
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "，Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.15;
    }
}
