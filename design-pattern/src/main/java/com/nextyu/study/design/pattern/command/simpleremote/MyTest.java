package com.nextyu.study.design.pattern.command.simpleremote;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        // 打开灯
        // 遥控器就是调用者，会传入一个命令对象，可以用来发出请求
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        // 电灯对象，此对象也就是请求的接收者
        Light light = new Light();
        // 命令对象，然后将接受者传给它
        LightOnCommand lightOnCommand = new LightOnCommand(light);

        // 把命令传给调用者
        simpleRemoteControl.setCommand(lightOnCommand);
        // 然后模拟按下按钮
        simpleRemoteControl.buttonWasPressed();

        // 打开车库的门
        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        simpleRemoteControl.setCommand(garageDoorOpenCommand);
        simpleRemoteControl.buttonWasPressed();
    }
}
