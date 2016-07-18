package com.nextyu.study.design.pattern.cor.Test;

import java.util.Random;

import com.nextyu.study.design.pattern.cor.handler.PriceHandlerFactory;
import org.junit.Test;


public class MyTest {

	@Test
	public void test() {
		Customer customer = new Customer();
		customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());
		Random random = new Random();
		for (int i = 1; i <= 100; i++) {
			System.out.print(i + ":");
			customer.requestDiscount(random.nextFloat());
		}
	}
}
