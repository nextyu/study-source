package com.nextyu.study.design.pattern.command.remote;

/**
 * 关闭音响.
 *
 * @author nextyu
 * @version 1.0
 */
public class StereoOffCommand implements Command {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
