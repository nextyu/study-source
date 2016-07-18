package com.nextyu.study.design.pattern.observer;

/**
 * 目前状况布告板.
 *
 * @author nextyu
 * @version 1.0
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    /**
     * 温度.
     */
    private float temperature;
    /**
     * 湿度.
     */
    private float humidity;

    /**
     * 主题.
     */
    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    /**
     * 显示最近的温度和湿度.
     */
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
