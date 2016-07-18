package com.nextyu.study.design.pattern.cor.Test;


import com.nextyu.study.design.pattern.cor.handler.PriceHandler;

/**
 * 客户，请求折扣.
 *
 * @author nextyu
 * @version 1.0
 */
public class Customer {
    private PriceHandler priceHandler;

    public void setPriceHandler(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    public void requestDiscount(float discount) {
        priceHandler.processDiscount(discount);
    }
}
