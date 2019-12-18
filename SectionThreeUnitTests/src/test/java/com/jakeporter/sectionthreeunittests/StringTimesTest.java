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
public class StringTimesTest {
    
    StringTimes test = new StringTimes();
    
    public StringTimesTest() {
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

    // zero and empty
    // zero and one
    // zero and two
    // one and empty
    // one and one
    // one and two
    // two and empty
    // two and one
    // two and two
    
    /**
     * Test of stringTimes method, of class StringTimes.
     */
    @Test
    public void test0EmptyString() {
        assertEquals("", test.stringTimes("", 0));
    }
    
    @Test
    public void test0OneCharString(){
        assertEquals("", test.stringTimes("a", 0));
    }
    
    @Test
    public void test0TwoCharString(){
        assertEquals("", test.stringTimes("ab", 0));
    }
    
    @Test
    public void test1EmptyString(){
        assertEquals("", test.stringTimes("", 1));
    }
    
    @Test
    public void test1OneCharString(){
        assertEquals("a", test.stringTimes("a", 1));
    }
    
    @Test
    public void test1TwoCharString(){
        assertEquals("ab", test.stringTimes("ab", 1));
    }
    
    @Test
    public void test2EmptyString(){
        assertEquals("", test.stringTimes("", 2));
    }
    
    @Test
    public void test2OneCharString(){
        assertEquals("aa", test.stringTimes("a", 2));
    }
    
    @Test
    public void test2TwoCharString(){
        assertEquals("abab", test.stringTimes("ab", 2));
    }
    
}
