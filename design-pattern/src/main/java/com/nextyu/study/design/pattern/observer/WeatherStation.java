package com.nextyu.study.design.pattern.observer;

/**
 * @author nextyu
 * @version 1.0
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4F);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.removeObserver(heatIndexDisplay);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
