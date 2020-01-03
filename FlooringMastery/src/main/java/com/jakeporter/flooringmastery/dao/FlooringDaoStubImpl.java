package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public class FlooringDaoStubImpl implements FlooringDao{

    Map <String, Order> orderMap = new HashMap();
    
    FlooringDaoStubImpl(){
        Order order1 = new Order();
        order1.setOrderNumber("1");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        orderMap.put(order1.getOrderNumber(), order1);
        
        Order order2 = new Order();
        order2.setOrderNumber("2");
        order2.setCustomerName("Kevin");
        order2.setState("VA");
        order2.setArea(new BigDecimal("80"));
        order2.setDateCreated(LocalDate.parse("06/20/1996", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setProductType("Laminate");
        order2.setCostPerSquareFoot(new BigDecimal("1.75"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        orderMap.put(order2.getOrderNumber(), order2);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return new ArrayList(orderMap.values());
    }

    @Override
    public BigDecimal getTaxRate(String state) {
        return new BigDecimal("5");
    }

    @Override
    public void loadProductInfo() throws TaxPersistenceException {
        // do nothing
    }

    @Override
    public void loadTaxRates() throws TaxPersistenceException {
        // do nothing
    }

    @Override
    public Map<String, Product> getAllProducts() {
        // iterate over map of numbers and orders
        // for each order, put its productInfo as a key and it's product into a map
        Map<String, Product> productMap = new HashMap();
        for (Map.Entry<String, Order> orderEntry : orderMap.entrySet()){
            productMap.put(orderEntry.getValue().getProductType(), orderEntry.getValue().getProductInfo());
        }
        return productMap;
    }

    @Override
    public Order addOrder(Order order) {
        return orderMap.put(order.getOrderNumber(), order);
    }

    @Override
    public Order deleteOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkIfTrainingMode() throws ConfigurationPersistenceException, UnknownConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setConfig(int configValue) throws ConfigurationPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeOrders() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrders() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
