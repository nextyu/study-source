package com.nextyu.study.design.pattern.command.party;


/**
 * @author nextyu
 * @version 1.0
 */
public class CeilingFanOnCommand implements Command {
    private CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        ceilingFan.high();
    }

    public void undo() {
        ceilingFan.off();
    }
}
