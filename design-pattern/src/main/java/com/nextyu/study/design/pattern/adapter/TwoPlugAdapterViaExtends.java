package com.nextyu.study.design.pattern.adapter;

/**
 * 二相转三相的插座适配器，采用继承方式的称为类适配器.
 *
 * @author nextyu
 * @version 1.0
 */
public class TwoPlugAdapterViaExtends extends TwoPlug implements ThreePlugIf {

    public void powerWithThree() {
        System.out.println("采用继承方式，使用二相转三相的插座适配器");
        super.powerWithTow();
    }

}
