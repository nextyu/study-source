package com.nextyu.study.design.pattern.command.remote;

/**
 * 音响.
 *
 * @author nextyu
 * @version 1.0
 */
public class Stereo {

    private String location;

    public Stereo(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " stereo is on");
    }

    public void off() {
        System.out.println(location + " stereo is off");
    }

    public void setCD() {
        System.out.println(location + " stereo is set for CD input");
    }

    public void setDVD() {
        System.out.println(location + " stereo is set for DVD input");
    }

    public void setRadio() {
        System.out.println(location + " stereo is set for Radio");
    }

    /**
     * 设置音量.
     *
     * @param volume
     */
    public void setVolume(int volume) {
        // code to set the volume
        // valid range: 1-11 (after all 11 is better than 10, right?)
        System.out.println(location + " Stereo volume set to " + volume);
    }
}
