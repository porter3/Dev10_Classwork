package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
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
}
