/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonEnd;

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
public class CommonEndTest {
    
    CommonEnd test = new CommonEnd();

    
    public CommonEndTest() {
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
    public void testSameFirst() {
        int[] a = {1,2,3};
        int[] b = {1,4,5};
        assertTrue(test.commonEnd(a, b));
    }
    
    @Test
    public void testSameLast(){
        int[] a = {1,2,3};
        int[] b = {4,5,3};
        assertTrue(test.commonEnd(a, b));
    }
    
    @Test
    public void testSameFirstAndLast(){
        int[] a = {1,2,3};
        int[] b = {1,5,3};
        assertTrue(test.commonEnd(a, b));
    }
    
    @Test
    public void testFirstAndLastIntsAreSame(){
        int[] a = {1,2,3};
        int[] b = {4,5,1};
        assertFalse(test.commonEnd(a, b));
    }
    
    @Test
    public void testNoSimilarities(){
        int[] a = {1,2,3};
        int[] b = {4,5,6};
        assertFalse(test.commonEnd(a, b));
    }
    
}
