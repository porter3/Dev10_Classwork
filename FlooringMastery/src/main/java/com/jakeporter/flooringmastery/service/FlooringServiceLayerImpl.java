package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.FlooringAuditDao;
import com.jakeporter.flooringmastery.dao.FlooringDao;
import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.OrderComparator;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Order> orderList = crudDao.getAllOrders();
        if (orderList.size() == 0){
            return 0;
        }
        else{
            Order orderWithHighestNumber = orderList.stream()
                .max(new OrderComparator())
                .get();
            return Integer.parseInt(orderWithHighestNumber.getOrderNumber());
        }
    }

    @Override
    public String generateOrderNumber(int highestOrderNumber) {
        // take the highest existing order number as a parameter,
            // add one to it, convert to String
        return Integer.toString(highestOrderNumber + 1);
    }
    
    @Override
    public void loadProductsAndTaxRates() throws TaxPersistenceException{
        crudDao.loadProductInfo();
        crudDao.loadTaxRates();
    }
    
    @Override
    public List<Product> getProductsAsList(){
        return new ArrayList(crudDao.getAllProducts().values());
    }
    
    @Override
    public Map<String, Product> getProductsAsMap(){
        return crudDao.getAllProducts();
    }

    @Override
    // Order should already be populated with customerName, area, state, productType
        // Needs taxRate, orderNumber, product costs(2), calculated values(4)
    public Order populateOrderFields(Order order) throws TaxPersistenceException{
        // convert tax rate from whole number to actual tax rate (i.e. 4 -> .04)
        BigDecimal taxRate = crudDao.getTaxRate(order.getState()).divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        // get highest order number, generate a new order number based off of that
        String orderNumber = generateOrderNumber(getHighestOrderNumber());
        // get product list to get product costs
        Map<String, Product> productMap = getProductsAsMap();
        // get product costs from map
        BigDecimal costPerSqFoot = productMap.get(order.getProductType()).getCostPerSquareFoot();
        BigDecimal laborCostPerSqFoot = productMap.get(order.getProductType()).getLaborCostPerSquareFoot();
        // set all of the Order's remaining non-calulated values
        order.setTaxRate(taxRate);
        order.setOrderNumber(orderNumber);
        order.setCostPerSquareFoot(costPerSqFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSqFoot);
        order.calculateCosts();
        return order;
    }
    
    @Override
    public Order addOrder(Order order){
        return crudDao.addOrder(order);
    }
    
    @Override
    public List<Order> getAllOrders(){
        return crudDao.getAllOrders();
    }
}
