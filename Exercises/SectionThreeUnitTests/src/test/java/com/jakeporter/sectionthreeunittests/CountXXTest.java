/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.sectionthreeunittests;

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
public class CountXXTest {
    
    CountXX test = new CountXX();
    
    public CountXXTest() {
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

    // x = 0
    // xabcxx = 1
    // xxaxx = 2
    // xxx = 2
    // xxxx = 3
    // xxxxx = 4
    
    @Test
    public void testX() {
        assertEquals(0, test.countXX("x"));
    }
    
    @Test
    public void testXABCXX(){
        assertEquals(1, test.countXX("xx"));
    }
    
    @Test
    public void testXXAXX(){
        assertEquals(2, test.countXX("xxaxx"));
    }
    
    @Test
    public void testXXX(){
        assertEquals(2, test.countXX("xxx"));
    }
    
    @Test
    public void testXXXX(){
        assertEquals(3, test.countXX("xxxx"));
    }
    
    @Test
    public void testXXXXX(){
        assertEquals(4, test.countXX("xxxxx"));
    }
}
