package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.ConfigurationPersistenceException;
import com.jakeporter.flooringmastery.dao.OrderPersistenceException;
import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dao.UnknownConfigurationException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public interface FlooringServiceLayer {

    public int getHighestOrderNumber();
    public String generateOrderNumber(int highestOrderNumber);
    public void loadProductsAndTaxRates() throws TaxPersistenceException;
    public List<Product> getProductsAsList();
    public Map<String, Product> getProductsAsMap();
    public Order populateOrderFields(Order order) throws TaxPersistenceException;
    public Order addOrder(Order order);
    public List<Order> getAllOrders();
    public List<Order> getOrdersFromDate(LocalDate orderDate);
    public Order checkOrderOnDate(List<Order> orderListOfDate, String orderNumber);
    public Order deleteOrder(Order orderForDeletion);
    public void setConfig(int configValue) throws ConfigurationPersistenceException;
    public boolean checkIfTrainingMode() throws ConfigurationPersistenceException, UnknownConfigurationException;
    public void saveOrders() throws OrderPersistenceException;
    public void loadOrders() throws OrderPersistenceException;
}
