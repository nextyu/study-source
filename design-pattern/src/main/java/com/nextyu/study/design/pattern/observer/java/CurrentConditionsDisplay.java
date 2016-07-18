package com.nextyu.study.design.pattern.observer.java;


import com.nextyu.study.design.pattern.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author nextyu
 * @version 1.0
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    /**
     * 温度.
     */
    private float temperature;
    /**
     * 湿度.
     */
    private float humidity;

    @Override
    public void update(Observable o, Object arg) {
        //拉取数据
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            temperature = weatherData.getTemperature();
            humidity = weatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}
