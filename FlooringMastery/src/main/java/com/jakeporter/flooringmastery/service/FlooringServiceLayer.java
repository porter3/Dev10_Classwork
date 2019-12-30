package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;

/**
 *
 * @author jake
 */
public interface FlooringServiceLayer {

    public int getHighestOrderNumber();
    public int generateOrderNumber(int highestOrderNumber);
    public void populateOrderFields(Order order) throws TaxPersistenceException;
}
