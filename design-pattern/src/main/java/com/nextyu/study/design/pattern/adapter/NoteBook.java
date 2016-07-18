package com.nextyu.study.design.pattern.adapter;

/**
 * 笔记本，需要使用三相电源接口.
 *
 * @author nextyu
 * @version 1.0
 */
public class NoteBook {

    private ThreePlugIf threePlugIf;

    public NoteBook(ThreePlugIf threePlugIf) {
        this.threePlugIf = threePlugIf;
    }

    /**
     * 使用插座充电.
     */
    public void charge() {
        threePlugIf.powerWithThree();
    }

}
