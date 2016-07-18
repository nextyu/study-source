package com.nextyu.study.design.pattern.decorator;

/**
 * 调料，奶泡，装饰者，具体实现.
 *
 * @author nextyu
 * @version 1.0
 */
public class Whip extends CondimentDecorator {

    /**
     * 被装饰者.
     */
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "，Whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
