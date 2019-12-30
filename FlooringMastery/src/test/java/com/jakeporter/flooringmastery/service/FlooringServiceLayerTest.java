/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.flooringmastery.service;

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
        // make a bunch of order objects in DAO
        
    }

    /**
     * Test of generateOrderNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testGenerateOrderNumber() {
    }
    
}
