package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author jake
 */
public class FlooringDaoStubImpl implements FlooringDao{

    List<Order> orderList;
    
    FlooringDaoStubImpl(){
        Order orderA = new Order();
        orderA
    }
    
    @Override
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
