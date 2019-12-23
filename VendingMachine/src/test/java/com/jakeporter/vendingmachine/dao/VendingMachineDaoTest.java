/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.vendingmachine.dao;

import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
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
public class VendingMachineDaoTest {
    
    VendingMachineDao crudDao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws InventoryPersistenceException{
        try{
            crudDao.clearInventory();
        }
        catch(InventoryPersistenceException e){
            throw new InventoryPersistenceException(e.getMessage());
        }
        
        System.out.println("SETUP IS RUNNING");

        Item doritos = new Item();
        doritos.setName("Doritos");
        doritos.setCost(new BigDecimal("249"));
        doritos.setInventoryCount(3);
        crudDao.addToInventory(doritos);
        
        Item popcorn = new Item();
        popcorn.setName("Popcorn");
        popcorn.setCost(new BigDecimal("199"));
        popcorn.setInventoryCount(2);
        crudDao.addToInventory(popcorn);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of writeAuditEntry method, of class VendingMachineAuditDao.
     */
    @Test
    public void testGetInventory() throws Exception{
        List<Item> inventory = crudDao.getInventory();
        assertEquals(inventory.size(), 2);
    }
    
    @Test
    public void testUpdateInventory() throws Exception{
        List<Item> inventory = crudDao.getInventory();
        Item popcorn = inventory.get(0);
        // ensure popcorn is actually at the 0th index
        String popcornName = popcorn.getName();
        assertTrue(popcornName.equals("Popcorn"));
        // ensure
        
        // update popcorn's inventory count from 2 to 1
        crudDao.updateInventory(popcorn);
        assertEquals(1, popcorn.getInventoryCount());
    }
    
    @Test
    public void testGetItem() throws Exception{
        Item popcorn1 = new Item();
        popcorn1.setName("Popcorn");
        popcorn1.setCost(new BigDecimal("199"));
        popcorn1.setInventoryCount(2);
        
        Item popcorn2 = crudDao.getItem("Popcorn");
        
        assertEquals(popcorn1, popcorn2);
    }
    
}
