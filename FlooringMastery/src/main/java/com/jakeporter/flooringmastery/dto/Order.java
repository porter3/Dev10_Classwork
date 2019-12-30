package com.jakeporter.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author jake
 */
public class Order {

    private Taxes taxInfo;
    private Product productInfo;
    
    private String orderNumber;
    private String customerName;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal orderTotal;

    public Taxes getTaxInfo() {
        return taxInfo;
    }
    
    public void setState(String state){
        taxInfo.setState(state);
    }
    
    public void setTaxRate(BigDecimal taxRate){
        taxInfo.setTaxRate(taxRate);
    }

    public Product getProductInfo() {
        return productInfo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void calculateMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void calulateLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void calculateTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void calulateOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    
}
