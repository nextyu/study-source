package com.nextyu.study.design.pattern.command.party;

/**
 * @author nextyu
 * @version 1.0
 */
public class TVOffCommand implements Command {

    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}
