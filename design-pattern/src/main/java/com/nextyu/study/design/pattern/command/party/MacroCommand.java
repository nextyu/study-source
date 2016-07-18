package com.nextyu.study.design.pattern.command.party;

/**
 * 宏命令，封装多个命令.
 *
 * @author nextyu
 * @version 1.0
 */
public class MacroCommand implements Command {

    private Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}
