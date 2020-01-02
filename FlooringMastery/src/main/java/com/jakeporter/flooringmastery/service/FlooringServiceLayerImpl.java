package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.FlooringAuditDao;
import com.jakeporter.flooringmastery.dao.FlooringDao;
import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.OrderComparator;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        /* if order number isn't populated yet (could be if an order is being edited and not created),
        get highest order number, generate a new order number based off of that
        */
        String orderNumber;
        if (order.getOrderNumber() == null){
            orderNumber = generateOrderNumber(getHighestOrderNumber());
        }
        else{
            orderNumber = order.getOrderNumber();
        }
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

    @Override
    public List<Order> getOrdersFromDate(LocalDate orderDate) {
        // get all orders in a list
        List<Order> orderList = new ArrayList(getAllOrders());
        // get new list with all orders for orderDate
        return orderList.stream()
                .filter(order -> order.getDateCreated().compareTo(orderDate) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public Order checkOrderOnDate(List<Order> orderListOfDate, String orderNumber) {
        // TODO: check if orderNumber has leading zeroes, add them if it doesn't
        
        Order orderToEdit = null;
        // iterate through orderListOfDate to see if an order with orderNumber if in there
        for (int i = 0; i < orderListOfDate.size(); i++){
            if (orderListOfDate.get(i).getOrderNumber().equals(orderNumber)){
                orderToEdit = orderListOfDate.get(i);
            }
        }
        return orderToEdit;
    }
}
