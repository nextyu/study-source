package com.nextyu.study.design.pattern.observer;

/**
 * 主题接口.
 *
 * @author nextyu
 * @version 1.0
 */
public interface Subject {
    /**
     * 注册观察者.
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者.
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者.
     */
    void notifyObservers();
}
