package com.nextyu.study.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nextyu
 * @version 1.0
 */
public class WeatherData implements Subject {

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

    /**
     * 观察者列表.
     */
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * 当从气象站得到更新观测值时，通知观察者.
     */
    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
