package com.nextyu.study.design.pattern.adapter;

/**
 * 二相转三相的插座适配器，采用组合方式的适配器成为对象适配器.
 *
 * @author nextyu
 * @version 1.0
 */
public class TwoPlugAdapterViaCombination implements ThreePlugIf {

    private TwoPlug twoPlug;

    public TwoPlugAdapterViaCombination(TwoPlug twoPlug) {
        this.twoPlug = twoPlug;
    }

    public void powerWithThree() {
        System.out.println("采用组合方式，使用二相转三相的插座适配器");
        twoPlug.powerWithTow();
    }

}
