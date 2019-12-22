package com.jakeporter.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author jake
 */
public class Item {

    private String name;
    private BigDecimal cost;
    private int inventoryCount;
    

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
}
