package com.nextyu.study.design.pattern.factory.simplefactory;

/**
 * @author nextyu
 * @version 1.0
 */
public class CheesePizza extends Pizza {
    public CheesePizza() {
        name = "Cheese Pizza";
        dough = "Regular Crust";
        sauce = "Marinara Pizza Sauce";
        toppings.add("Fresh Mozzarella");
        toppings.add("Parmesan");
    }
}
