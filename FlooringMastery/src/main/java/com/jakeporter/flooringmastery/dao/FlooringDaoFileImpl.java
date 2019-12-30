package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public class FlooringDaoFileImpl implements FlooringDao{
    
    private Map<String, Order> orders = new HashMap();
    private final String DELIMITER = "::";

    @Override
    public List<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList(orders.values());
        return orderList;
    }
    
}
