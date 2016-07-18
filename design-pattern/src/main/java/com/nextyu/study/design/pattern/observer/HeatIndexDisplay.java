package com.nextyu.study.design.pattern.observer;

/**
 * 酷热指数布告板.
 * 酷热指数是一个结合温度和湿度的指数，用来显示人的温度感受。可以利用温度T和相对湿
 * 度RH套用下面的公式来计算酷热指数.
 *
 * @author nextyu
 * @version 1.0
 */
public class HeatIndexDisplay implements Observer, DisplayElement {

    /**
     * 酷热指数.
     */
    private float heatIndex;

    public HeatIndexDisplay(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Heat index is " + heatIndex);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        heatIndex = computeHeatIndex(temperature, humidity);
        display();
    }

    private float computeHeatIndex(float t, float rh) {
        float index = (float) ((16.923 + (0.185212 * t) + (5.37941 * rh)
                - (0.100254 * t * rh) + (0.00941695 * (t * t))
                + (0.00728898 * (rh * rh)) + (0.000345372 * (t * t * rh))
                - (0.000814971 * (t * rh * rh))
                + (0.0000102102 * (t * t * rh * rh))
                - (0.000038646 * (t * t * t)) + (0.0000291583 * (rh * rh * rh))
                + (0.00000142721 * (t * t * t * rh))
                + (0.000000197483 * (t * rh * rh * rh))
                - (0.0000000218429 * (t * t * t * rh * rh)) + 0.000000000843296 * (t
                * t * rh * rh * rh)) - (0.0000000000481975 * (t * t * t * rh
                * rh * rh)));
        return index;
    }


}
