package com.nextyu.study.design.pattern.observer;

/**
 * 观察者接口.
 *
 * @author nextyu
 * @version 1.0
 */
public interface Observer {
    /**
     * @param temperature     温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temperature, float humidity, float pressure);
}
