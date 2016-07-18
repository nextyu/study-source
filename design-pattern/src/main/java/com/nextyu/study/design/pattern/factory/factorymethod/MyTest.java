package com.nextyu.study.design.pattern.factory.factorymethod;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
