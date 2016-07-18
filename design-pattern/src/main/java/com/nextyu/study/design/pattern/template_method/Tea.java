package com.nextyu.study.design.pattern.template_method;

/**
 * 具体子类，提供了制备茶的具体实现.
 * 
 * @author nextyu
 * @version 1.0
 */
public class Tea extends RefreshBeverage {

	@Override
	protected void brew() {
		System.out.println("用80度的热水浸泡茶叶5分钟");
	}

	@Override
	protected void addCondiments() {
		System.out.println("加入柠檬");
	}

}
