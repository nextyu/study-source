package com.nextyu.study.design.pattern.observer.java;

/**
 * @author nextyu
 * @version 1.0
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.addObserver(new CurrentConditionsDisplay());
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
