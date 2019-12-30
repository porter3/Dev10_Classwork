package com.jakeporter.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author jake
 */
class Taxes {

    private String state;
    private BigDecimal taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
}
