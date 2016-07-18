package com.nextyu.study.design.pattern.factory.test;


import com.nextyu.study.design.pattern.factory.factorymethod.NYPizzaStore;
import com.nextyu.study.design.pattern.factory.factorymethod.Pizza;
import com.nextyu.study.design.pattern.factory.factorymethod.PizzaStore;
import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest1 {
    @Test
    public void test1() {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
    }
}
