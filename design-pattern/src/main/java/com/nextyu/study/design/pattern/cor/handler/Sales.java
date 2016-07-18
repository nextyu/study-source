package com.nextyu.study.design.pattern.cor.handler;

/**
 * 销售， 可以批准5%以内的折扣.
 * 
 * @author nextyu
 * @version 1.0
 */
public class Sales extends PriceHandler {

	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.05) {
			System.out.format("%s批准了折扣：%.2f%n", this.getClass().getName(),
					discount);
		} else {
			successor.processDiscount(discount);
		}
	}

}
