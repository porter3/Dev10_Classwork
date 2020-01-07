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
public class GreatPartyTest {
    
    GreatParty party = new GreatParty(); 
    
    public GreatPartyTest() {
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
     * Test of greatPartyCheck method, of class GreatParty.
     */
    @org.junit.jupiter.api.Test
    public void test30False() {
        // method is verifying that 'false' is returned
         assertFalse(party.greatParty(30, false));
    }
    
    @Test
    public void test50False(){
        // method is verifying that 'true' is returned
        assertTrue(party.greatParty(50, false));
    }
    
    @Test
    public void test70True(){
        assertTrue(party.greatParty(70, true));
    }
    
    @Test
    public void test39True(){
        assertFalse(party.greatParty(39, true));
    }
    
    @Test
    public void test39False(){
        assertFalse(party.greatParty(39, false));
    }
    
    @Test
    public void test40True(){
        assertTrue(party.greatParty(40, true));
    }
    
    @Test
    public void test40False(){
        assertTrue(party.greatParty(40, false));
    }
    
    @Test
    public void test60True(){
        assertTrue(party.greatParty(60, true));
    }
    
    @Test
    public void test60False(){
        assertTrue(party.greatParty(60, false));
    }
    
    @Test
    public void test61True(){
        assertTrue(party.greatParty(61, true));
    }
    
    @Test
    public void test61False(){
        assertFalse(party.greatParty(61, false));
    }
}
