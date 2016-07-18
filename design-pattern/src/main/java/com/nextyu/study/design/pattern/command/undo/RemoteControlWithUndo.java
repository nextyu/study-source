package com.nextyu.study.design.pattern.command.undo;


/**
 * 遥控器，调用者.
 *
 * @author nextyu
 * @version 1.0
 */
public class RemoteControlWithUndo {

    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControlWithUndo() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }

        undoCommand = noCommand;
    }

    /**
     * @param index      插槽位置
     * @param onCommand  开的命令
     * @param offCommand 关的命令
     */
    public void setCommand(int index, Command onCommand, Command offCommand) {
        onCommands[index] = onCommand;
        offCommands[index] = offCommand;
    }

    public void onButtonWasPushed(int index) {
        onCommands[index].execute();
        undoCommand = onCommands[index];
    }

    public void offButtonWasPushed(int index) {
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuilder.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                    + "    " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuilder.toString();
    }
}
