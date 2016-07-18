package com.nextyu.study.design.pattern.template_method;

import org.junit.Test;

public class MyTest {

	@Test
	public void testCoffee() {
		RefreshBeverage refreshBeverage = new Coffee();
		refreshBeverage.prepareBeverageTemplate();
	}

	@Test
	public void testTea() {
		RefreshBeverage refreshBeverage = new Tea();
		refreshBeverage.prepareBeverageTemplate();
	}

	@Test
	public void testTraditionalTea() {
		RefreshBeverage refreshBeverage = new TraditionalTea();
		refreshBeverage.prepareBeverageTemplate();
	}

}
