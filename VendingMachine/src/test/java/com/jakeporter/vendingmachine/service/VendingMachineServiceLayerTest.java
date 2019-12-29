/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.vendingmachine.service;

import com.jakeporter.vendingmachine.dao.VendingMachineAuditDao;
import com.jakeporter.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.jakeporter.vendingmachine.dao.VendingMachineDao;
import com.jakeporter.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest(){
//        VendingMachineDao crudDao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//        
//        service = new VendingMachineServiceLayerImpl(crudDao, auditDao);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
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
     * Test of getInventory method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetInventory() throws Exception {
        assertEquals(1, service.getInventory().size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        // asserting that something comes back if asked for it
        Item testItem = service.getItem("Cookies");
        assertNotNull(testItem);
        testItem = service.getItem("not a real item");
        assertNull(testItem);
    }

    /**
     * Test of calculateUserChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCalculateUserChange() {
        Item cookies = new Item();
        cookies.setName("Cookies");
        cookies.setCost(new BigDecimal("200"));
        cookies.setInventoryCount(3);
        
        Change testChange = service.calculateUserChange(new BigDecimal("5.00"), cookies);
        assertEquals(testChange.getTotalChangeInDollars(), new BigDecimal("3.00"));
    }

    /**
     * Test of validateFunds method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidateFunds() throws Exception {
        Item cookies = new Item();
        cookies.setName("Cookies");
        cookies.setCost(new BigDecimal("200"));
        cookies.setInventoryCount(3);
        
        // should validate
        try{
            service.validateFunds(new BigDecimal("300"), cookies);
        }
        catch(InsufficientFundsException e){
            fail();
        }
    
        // should not validate
        try{
            service.validateFunds(new BigDecimal("100"), cookies);
        }
        catch(InsufficientFundsException e){
            return;
        }
        
        // corner case, should validate
        try{
            service.validateFunds(new BigDecimal("200"), cookies);
        }
        catch(InsufficientFundsException e){
            fail();
        }
    }

    /**
     * Test of validateInventory method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidateInventory() throws Exception {
        
        Item cookies = new Item();
        cookies.setName("Cookies");
        cookies.setCost(new BigDecimal("200"));
        cookies.setInventoryCount(3);
        
        // item exists and has inventory, should validate
        try{
            service.validateInventory(cookies);
        }
        catch(NullPointerException | NoItemInventoryException e){
            fail();
        }
        
        // no inventory, should not validate
        cookies.setInventoryCount(0);
        try{
            service.validateInventory(cookies);
        }
        catch(NoItemInventoryException e){
            return;
        }
        
        // corner case, should validate
        cookies.setInventoryCount(1);
        try{
            service.validateInventory(cookies);
        }
        catch(NoItemInventoryException e){
            fail();
        }
        
    }
    
}
