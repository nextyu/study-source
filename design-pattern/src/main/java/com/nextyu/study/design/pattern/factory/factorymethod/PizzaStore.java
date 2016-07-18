package com.nextyu.study.design.pattern.factory.factorymethod;

/**
 * @author nextyu
 * @version 1.0
 */
public abstract class PizzaStore {

    /**
     * 抽象工厂方法，让子类实现此方法制造产品.
     *
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("--- Making a " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
