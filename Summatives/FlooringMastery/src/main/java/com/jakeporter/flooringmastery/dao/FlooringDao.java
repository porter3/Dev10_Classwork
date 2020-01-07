package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public interface FlooringDao {

    public List<Order> getAllOrders();
    public BigDecimal getTaxRate(String state);
    public void loadProductInfo() throws TaxPersistenceException;
    public void loadTaxRates() throws TaxPersistenceException;
    public Map<String, String> getStateTaxRates();
    public Map<String, Product> getAllProducts();
    public Order addOrder(Order order);
    public Order deleteOrder(Order order);
    public boolean checkIfTrainingMode() throws ConfigurationPersistenceException, UnknownConfigurationException;
    public void setConfig(int configValue) throws ConfigurationPersistenceException;
    public void writeOrders() throws OrderPersistenceException;
    public void loadOrders() throws OrderPersistenceException;
}
