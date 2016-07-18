package com.nextyu.study.design.pattern.cor.handler;

/**
 * @author nextyu
 * @version 1.0
 */
public class PriceHandlerFactory {

	/**
	 * 创建PriceHandler的工厂方法
	 * @return
	 */
	public static PriceHandler createPriceHandler() {
		
		Sales sales = new Sales();
		Lead lead = new Lead();
		Manager manager = new Manager();
		Director director = new Director();
		VicePresident vicePresident = new VicePresident();
		CEO ceo = new CEO();
		
		sales.setSuccessor(lead);
		lead.setSuccessor(manager);
		manager.setSuccessor(director);
		director.setSuccessor(vicePresident);
		vicePresident.setSuccessor(ceo);
		
		return sales;
		
	}
	
}
