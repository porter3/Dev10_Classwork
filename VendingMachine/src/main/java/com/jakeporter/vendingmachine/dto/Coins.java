/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author jake
 */
public enum Coins {
    PENNIES(new BigDecimal("1")),
    NICKELS(new BigDecimal("5")),
    DIMES(new BigDecimal("10")),
    QUARTERS(new BigDecimal("25"));
    
    private final BigDecimal value;
    
    Coins(BigDecimal value){
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
