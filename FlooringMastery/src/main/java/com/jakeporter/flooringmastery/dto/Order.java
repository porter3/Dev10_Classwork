package com.jakeporter.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author jake
 */
public class Order {

    public Taxes taxInfo = new Taxes();
    private Product productInfo = new Product();
    
    private String orderNumber;
    private String customerName;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal orderTotal;
    
    private LocalDate dateCreated;

    public Taxes getTaxInfo() {
        return taxInfo;
    }
    
    public void setState(String state){
        taxInfo.setState(state);
    }
    
    public String getState(){
        return taxInfo.getState();
    }
    
    public void setTaxRate(BigDecimal taxRate){
        taxInfo.setTaxRate(taxRate);
    }

    public String getProductType() {
        return productInfo.getProductType();
    }
    
    public void setProductType(String productType) {
        productInfo.setProductType(productType);
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

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOrderTotal() {
        return orderTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void calculateCosts() {
        this.materialCost = productInfo.getCostPerSquareFoot().multiply(area);
        this.laborCost = productInfo.getLaborCostPerSquareFoot().multiply(area);
        BigDecimal laborAndMaterialCosts = materialCost.add(laborCost);
        this.totalTax = laborAndMaterialCosts.multiply(taxInfo.getTaxRate());
        this.orderTotal = laborAndMaterialCosts.add(totalTax);
    }
    
    public void setCostPerSquareFoot(BigDecimal costPerSqFoot){
        productInfo.setCostPerSquareFoot(costPerSqFoot);
    }
    
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSqFoot){
        productInfo.setLaborCostPerSquareFoot(laborCostPerSqFoot);
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
