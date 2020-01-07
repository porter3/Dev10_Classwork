package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jake
 */
public class FlooringServiceLayerTest {
    
    FlooringServiceLayer service;
    
    public FlooringServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getHighestOrderNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetHighestOrderNumber() {
        assertEquals(2, service.getHighestOrderNumber());
    }

    /**
     * Test of generateOrderNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testGenerateOrderNumber() {
        int highest = service.getHighestOrderNumber();
        assertEquals("00003", service.generateOrderNumber(highest));
    }

    /**
     * Test of loadProductsAndTaxRates method, of class FlooringServiceLayer.
     */
    @Test
    public void testLoadProductsAndTaxRates() throws Exception {
        // Pass-through: stubbed dao implementations do nothing
        service.loadProductsAndTaxRates();
    }

    /**
     * Test of getProductsAsList method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetProductsAsList() {
        Order order1 = new Order();
        order1.setOrderNumber("1");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        assertEquals(2, service.getProductsAsList().size());
        assertEquals(order1.getProductInfo(), service.getProductsAsList().get(0));
    }

    /**
     * Test of getProductsAsMap method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetProductsAsMap() {
        Order order1 = new Order();
        order1.setOrderNumber("1");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        assertEquals(2, service.getProductsAsMap().size());
        assertEquals(order1.getProductInfo(), service.getProductsAsMap().get("Wood"));
    }

    /**
     * Test of populateOrderFields method, of class FlooringServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testPopulateOrderFields() throws Exception {
        Order order1 = new Order();
        order1.setOrderNumber("00003");
        order1.setCustomerName("Jake");
        order1.setState("NJ");
        order1.setTaxRate(new BigDecimal(".05").setScale(4));
        order1.setArea(new BigDecimal("100"));
        order1.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setProductType("Wood");
        order1.setCostPerSquareFoot(new BigDecimal("5.15"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order1.calculateCosts();
        
        Order order2 = new Order();
        order2.setCustomerName("Jake");
        order2.setState("NJ");
        order2.setArea(new BigDecimal("100"));
        order2.setDateCreated(LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setProductType("Wood");
        order2 = service.populateOrderFields(order2);
        
        assertTrue(order1.equals(order2));
    }

    /**
     * Test of addOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception{
        // expect back an order object, size of 1 in DAO
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
        
        service.addOrder(order1);
        assertEquals(3, service.getAllOrders().size());
    }

    /**
     * Test of getAllOrders method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
        assertEquals(2, service.getAllOrders().size());
    }

    /**
     * Test of getOrdersFromDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrdersFromDate() {
        LocalDate march10 = LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        List<Order> orderList = service.getAllOrders();
        List<Order> orderListOfDate = new ArrayList();
        for (Order order : orderList){
            if(order.getDateCreated().compareTo(march10) == 0){
                orderListOfDate.add(order);
            }
        }
        assertEquals(1, orderListOfDate.size());
    }

    /**
     * Test of checkOrderOnDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testCheckOrderOnDate() {
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
        
        service.addOrder(order1);
        
        List<Order> orderList = service.getAllOrders();
        Order orderBeingVerified = service.checkOrderOnDate(orderList, order1.getOrderNumber());
        assertNotNull(orderBeingVerified);
    }
    
}
