package com.nextyu.study.design.pattern.command.simpleremote;

/**
 * 打开电灯命令.
 *
 * @author nextyu
 * @version 1.0
 */
public class LightOnCommand implements Command {

    /**
     * 电灯，接收者.
     */
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
