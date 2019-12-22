/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.vendingmachine.dto;

import java.math.BigDecimal;
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
public class ChangeTest {
    
    public ChangeTest() {
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

    @Test
    public void testCalculateChange() {
        BigDecimal fourNinety = new BigDecimal("490");
        Change testChange = new Change(fourNinety);
        BigDecimal nineteen = new BigDecimal("19");
        assertEquals(testChange.getQuarters(), nineteen);
        
        BigDecimal fiveSeventeen = new BigDecimal("517");
        testChange = new Change(fiveSeventeen);
        BigDecimal two = new BigDecimal("2");
        assertEquals(testChange.getPennies(), two);
    }
    
}
