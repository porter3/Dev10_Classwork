package com.jakeporter.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

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

    public Product getProductInfo() {
        return productInfo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.taxInfo);
        hash = 97 * hash + Objects.hashCode(this.productInfo);
        hash = 97 * hash + Objects.hashCode(this.orderNumber);
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.materialCost);
        hash = 97 * hash + Objects.hashCode(this.laborCost);
        hash = 97 * hash + Objects.hashCode(this.totalTax);
        hash = 97 * hash + Objects.hashCode(this.orderTotal);
        hash = 97 * hash + Objects.hashCode(this.dateCreated);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.taxInfo, other.taxInfo)) {
            return false;
        }
        if (!Objects.equals(this.productInfo, other.productInfo)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.orderTotal, other.orderTotal)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        return true;
    }
}
