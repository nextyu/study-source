package com.nextyu.study.design.pattern.command.remote;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        // 遥控器
        RemoteControl remoteControl = new RemoteControl();

        // 各种装置
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo livingRoomStereo = new Stereo("Living Room");

        // 命令
        LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);

        StereoOnWithCDCommand livingRoomStereoOnWithCDCommand = new StereoOnWithCDCommand(livingRoomStereo);
        StereoOffCommand livingRoomStereoOffCommand = new StereoOffCommand(livingRoomStereo);

        remoteControl.setCommand(0, livingRoomLightOnCommand, livingRoomLightOffCommand);
        remoteControl.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);
        remoteControl.setCommand(2, livingRoomStereoOnWithCDCommand, livingRoomStereoOffCommand);

        System.out.println(remoteControl);


        for (int i = 0; i < 7; i++) {
            remoteControl.onButtonWasPushed(i);
            remoteControl.offButtonWasPushed(i);
        }

    }

}
