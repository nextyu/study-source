package com.nextyu.study.design.pattern.command.party;

/**
 * @author nextyu
 * @version 1.0
 */
public class TVOnCommand implements Command {

    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}
