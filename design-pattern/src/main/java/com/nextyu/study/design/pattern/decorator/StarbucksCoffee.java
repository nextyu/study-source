package com.nextyu.study.design.pattern.decorator;

/**
 * @author nextyu
 * @version 1.0
 */
public class StarbucksCoffee {
    public static void main(String[] args) {
        //订一杯Espresso，不需要调料，打印出它的描述与价钱。
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        //加点摩卡
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        //再加点摩卡
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        //加点奶泡
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }
}
