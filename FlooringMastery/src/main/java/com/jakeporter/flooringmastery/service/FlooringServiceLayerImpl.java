package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.FlooringAuditDao;
import com.jakeporter.flooringmastery.dao.FlooringDao;
import com.jakeporter.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author jake
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer{

    private FlooringDao crudDao;
    private FlooringAuditDao auditDao;
    
    FlooringServiceLayerImpl(FlooringDao crudDao, FlooringAuditDao auditDao){
        this.crudDao = crudDao;
        this.auditDao = auditDao;
    }

    @Override
    public int getHighestOrderNumber() {
        // load all orders from data structure
        List<Order> orders = crudDao.getAllOrders();
        // aggregate operation to find highest number
    }

    @Override
    public int generateOrderNumber(int highestOrderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
