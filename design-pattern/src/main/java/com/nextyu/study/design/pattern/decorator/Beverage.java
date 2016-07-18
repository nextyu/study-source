package com.nextyu.study.design.pattern.decorator;

/**
 * 饮料，抽象类.
 *
 * @author nextyu
 * @version 1.0
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
