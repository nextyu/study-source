package com.nextyu.study.design.pattern.factory.simplefactory;

/**
 * 披萨店，把创建披萨委托给披萨工厂.
 *
 * @author nextyu
 * @version 1.0
 */
public class PizzaStore {
    private SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
