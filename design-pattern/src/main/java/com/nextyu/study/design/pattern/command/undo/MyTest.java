package com.nextyu.study.design.pattern.command.undo;

import org.junit.Test;

/**
 * @author nextyu
 * @version 1.0
 */
public class MyTest {

    @Test
    public void test() {
        RemoteControlWithUndo remoteControlWithUndo = new RemoteControlWithUndo();

        CeilingFan ceilingFan = new CeilingFan("Living Room");
        CeilingFanMediumCommand ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanHighCommand ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);

        remoteControlWithUndo.setCommand(0, ceilingFanMediumCommand, ceilingFanOffCommand);
        remoteControlWithUndo.setCommand(1, ceilingFanHighCommand, ceilingFanOffCommand);

        System.out.println(remoteControlWithUndo);

        remoteControlWithUndo.onButtonWasPushed(0);
        remoteControlWithUndo.offButtonWasPushed(0);
        remoteControlWithUndo.undoButtonWasPushed();

        remoteControlWithUndo.onButtonWasPushed(1);
        remoteControlWithUndo.offButtonWasPushed(0);
        remoteControlWithUndo.undoButtonWasPushed();
    }

}
