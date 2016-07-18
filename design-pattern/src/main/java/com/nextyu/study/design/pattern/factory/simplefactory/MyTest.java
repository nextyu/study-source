package com.nextyu.study.design.pattern.factory.simplefactory;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(factory);

        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println("We ordered a " + pizza.getName() + "\n");

        pizza = pizzaStore.orderPizza("veggie");
        System.out.println("We ordered a " + pizza.getName() + "\n");
    }
}
