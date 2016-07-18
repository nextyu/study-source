package com.nextyu.study.design.pattern.command.remote;

/**
 * 电灯.
 *
 * @author nextyu
 * @version 1.0
 */
public class Light {

    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is on");
    }

    public void off() {
        System.out.println(location + " light is off");
    }

}
