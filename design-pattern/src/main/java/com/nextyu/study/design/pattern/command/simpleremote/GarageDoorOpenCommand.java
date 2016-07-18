package com.nextyu.study.design.pattern.command.simpleremote;

/**
 * 打开车库的门命令.
 *
 * @author nextyu
 * @version 1.0
 */
public class GarageDoorOpenCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
