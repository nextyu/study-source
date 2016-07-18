package com.nextyu.study.design.pattern.decorator;

/**
 * 装饰者，抽象类.
 *
 * @author nextyu
 * @version 1.0
 */
public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}
