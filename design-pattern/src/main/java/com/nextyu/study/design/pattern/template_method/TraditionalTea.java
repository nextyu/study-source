package com.nextyu.study.design.pattern.template_method;

public class TraditionalTea extends RefreshBeverage {

	@Override
	protected void brew() {
		System.out.println("用80度的热水浸泡茶叶5分钟");
	}

	@Override
	protected void addCondiments() {

	}

	/**
	 * 子类通过覆盖的形式选择挂载钩子函数.
	 */
	@Override
	protected boolean isCustomerWantsCondiments() {
		return false;
	}

}
