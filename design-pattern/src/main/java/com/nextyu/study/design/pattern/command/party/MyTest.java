package com.nextyu.study.design.pattern.command.party;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        RemoteControl remoteControl = new RemoteControl();

        TV livingRoomTV = new TV("Living Room");
        Light livingRoomLight = new Light("Living Room");
        Stereo livingRoomStereo = new Stereo("Living Room");

        TVOnCommand livingRoomTVOnCommand = new TVOnCommand(livingRoomTV);
        LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        StereoOnWithCDCommand livingRoomStereoOnWithCDCommand = new StereoOnWithCDCommand(livingRoomStereo);

        TVOffCommand livingRoomTVOffCommand = new TVOffCommand(livingRoomTV);
        LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
        StereoOffCommand livingRoomStereoOffCommand = new StereoOffCommand(livingRoomStereo);

        Command[] partyOn = {livingRoomTVOnCommand, livingRoomLightOnCommand, livingRoomStereoOnWithCDCommand};
        Command[] partyOff = {livingRoomTVOffCommand, livingRoomLightOffCommand, livingRoomStereoOffCommand};

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

        System.out.println(remoteControl);
        System.out.println("--- Pushing Macro On---");
        remoteControl.onButtonWasPushed(0);
        System.out.println("--- Pushing Macro Off---");
        remoteControl.offButtonWasPushed(0);

        System.out.println("--- undo ---");
        remoteControl.undoButtonWasPushed();

    }
}
