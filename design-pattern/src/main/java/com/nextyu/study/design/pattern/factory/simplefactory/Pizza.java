package com.nextyu.study.design.pattern.factory.simplefactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nextyu
 * @version 1.0
 */
public abstract class Pizza {
    /**
     * 名称.
     */
    protected String name;
    /**
     * 面团类型.
     */
    protected String dough;
    /**
     * 酱料类型.
     */
    protected String sauce;

    /**
     * 一套佐料.
     */
    protected List<String> toppings = new ArrayList<>();

    public String getName() {
        return name;
    }

    /**
     * 准备.
     */
    public void prepare() {
        System.out.println("Preparing " + name);
    }

    /**
     * 烘烤.
     */
    public void bake() {
        System.out.println("Baking " + name);
    }

    /**
     * 切片.
     */
    public void cut() {
        System.out.println("Cutting " + name);
    }

    /**
     * 装盒.
     */
    public void box() {
        System.out.println("Boxing " + name);
    }

    public String toString() {
        // code to display pizza name and ingredients
        StringBuffer display = new StringBuffer();
        display.append("---- " + name + " ----\n");
        display.append(dough + "\n");
        display.append(sauce + "\n");
        for (int i = 0; i < toppings.size(); i++) {
            display.append(toppings.get(i) + "\n");
        }
        return display.toString();
    }

}
