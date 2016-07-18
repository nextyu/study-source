package com.nextyu.study.design.pattern.command.party;

/**
 * 命令接口.
 *
 * @author nextyu
 * @version 1.0
 */
public interface Command {

    void execute();

    void undo();
}
