package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author jake
 */
public interface FlooringDao {

    public List<Order> getAllOrders();
}
