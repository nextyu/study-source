package com.nextyu.study.design.pattern.command.remote;


/**
 * @author nextyu
 * @version 1.0
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
