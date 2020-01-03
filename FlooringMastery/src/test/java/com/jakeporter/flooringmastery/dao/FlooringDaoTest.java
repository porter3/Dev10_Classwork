/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jake
 */
public class FlooringDaoTest {
    
    FlooringDao crudDao = new FlooringDaoFileImpl();
    
    public FlooringDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        FlooringDaoFileImpl.ORDER_FILE = "testOrders.txt";
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // clear out data structure
        List<Order> orderList = crudDao.getAllOrders();
        for (Order order : orderList){
            // delete each order from map
            crudDao.deleteOrder(order);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addOrder and getAllOrders methods, of class FlooringDao.
     */
    @Test
    public void testAddGetAllOrders() {
        // add a couple orders, assert the size of list is 2
        Order order1 = new Order();
        order1.setOrderNumber("3");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setTaxRate(new BigDecimal(".05").setScale(4));
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order1.calculateCosts();
        crudDao.addOrder(order1);
        
        Order order2 = new Order();
        order2.setOrderNumber("4");
        order2.setCustomerName("Jake");
        order2.setState("NJ");
        order2.setTaxRate(new BigDecimal(".05").setScale(4));
        order2.setArea(new BigDecimal("100"));
        order2.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setProductType("Wood");
        order2.setCostPerSquareFoot(new BigDecimal("5.15"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order2.calculateCosts();
        crudDao.addOrder(order2);
        
        assertEquals(2, crudDao.getAllOrders().size());
    }

    /**
     * Test of loadTaxRates and getTaxRate methods, of class FlooringDao.
     */
    @Test
    public void testLoadGetTaxRate() throws Exception {
        crudDao.loadTaxRates();
        BigDecimal ncTaxRate = new BigDecimal(".0475");
        BigDecimal testNcTaxRate = crudDao.getTaxRate("NC").divide(new BigDecimal("100")).setScale(4);
        assertTrue(ncTaxRate.equals(testNcTaxRate));
    }

    /**
     * Test of loadProductInfo and getAllProducts methods, of class FlooringDao.
     */
    @Test
    public void testLoadProductInfoAndGetAllProducts() throws Exception {
        crudDao.loadProductInfo();
        assertEquals(4, crudDao.getAllProducts().size());
    }

    /**
     * Test of deleteOrder method, of class FlooringDao.
     */
    @Test
    public void testDeleteOrder() {
        Order order1 = new Order();
        order1.setOrderNumber("3");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setTaxRate(new BigDecimal(".05").setScale(4));
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order1.calculateCosts();
        crudDao.addOrder(order1);
        assertEquals(1, crudDao.getAllOrders().size());
        
        crudDao.deleteOrder(order1);
        assertEquals(0, crudDao.getAllOrders().size());
    }

    /**
     * Test of setConfig and checkIfTrainingMode methods, of class FlooringDao.
     */
    @Test
    public void testSetConfigAndCheckIfTrainingMode() throws Exception {
        // set to Production mode
        crudDao.setConfig(1);
        assertFalse(crudDao.checkIfTrainingMode());
        
        crudDao.setConfig(2);
        assertTrue(crudDao.checkIfTrainingMode());
    }

    /**
     * Test of writeOrders method, of class FlooringDao.
     */
    @Test
    public void testWriteLoadOrders() throws Exception {
        Order order1 = new Order();
        order1.setOrderNumber("3");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setTaxRate(new BigDecimal(".05").setScale(4));
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order1.calculateCosts();
        crudDao.addOrder(order1);
        assertEquals(1, crudDao.getAllOrders().size());
        
        // write orders to temp file
        crudDao.writeOrders();
        
        // delete order from DAO
        crudDao.deleteOrder(order1);
        assertEquals(0, crudDao.getAllOrders().size());
        
        // load orders from file into DAO
        crudDao.loadOrders();
        
        // test that the one order written earlier has been loaded back in
        assertEquals(1, crudDao.getAllOrders().size());
    }
}
