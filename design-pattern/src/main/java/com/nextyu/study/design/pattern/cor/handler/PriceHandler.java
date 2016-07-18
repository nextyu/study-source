package com.nextyu.study.design.pattern.cor.handler;

/**
 * 责任链设计模式.
 * 价格处理人，负责处理客户折扣申请.
 * @author nextyu
 * @version 1.0
 */
public abstract class PriceHandler {
	
	/**
	 * 直接后继，用于传递请求.
	 */
	protected PriceHandler successor;
	
	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}
	
	/**
	 * 处理折扣申请.
	 * @param discount
	 */
	public abstract void processDiscount(float discount);
	
}
