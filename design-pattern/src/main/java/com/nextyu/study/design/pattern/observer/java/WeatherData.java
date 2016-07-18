package com.nextyu.study.design.pattern.observer.java;

import java.util.Observable;

/**
 * @author nextyu
 * @version 1.0
 */
public class WeatherData extends Observable {

    /**
     * 温度.
     */
    private float temperature;
    /**
     * 湿度.
     */
    private float humidity;
    /**
     * 气压.
     */
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    /**
     * 当从气象站得到更新观测值时，通知观察者.
     */
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

}
