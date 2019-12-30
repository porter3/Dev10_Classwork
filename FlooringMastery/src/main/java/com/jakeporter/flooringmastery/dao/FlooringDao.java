package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jake
 */
public interface FlooringDao {

    public List<Order> getAllOrders();
    public BigDecimal getTaxRate(String state);
    public void loadTaxRates() throws TaxPersistenceException;
}
