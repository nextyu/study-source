package com.nextyu.study.design.pattern.command.simpleremote;

/**
 * 简单的遥控器.
 *
 * @author nextyu
 * @version 1.0
 */
public class SimpleRemoteControl {
    /**
     * 命令，有一个插槽持有命令，而这个命令控制着一个装置.
     */
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed() {
        command.execute();
    }
}
