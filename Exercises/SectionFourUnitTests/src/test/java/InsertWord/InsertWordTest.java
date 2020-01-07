/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InsertWord;

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
public class InsertWordTest {
    
    InsertWord test = new InsertWord();
        
    public InsertWordTest() {
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
    public void testOneAndOne() {
        assertTrue(test.insertWord("<>", "H").equals("<H>"));
    }
    
    @Test
    public void testOneAndThree(){
        assertTrue(test.insertWord("::", "dog").equals(":dog:"));
    }
    
    @Test
    public void testTwoAndOne(){
        assertTrue(test.insertWord("(())", "J").equals("((J))"));
    }
    
    @Test
    public void testTwoAndTwo(){
        
    }
    
    @Test
    public void testThreeAndOne(){
        
    }
    
    @Test
    public void testThreeAndThree(){
        
    }
    
}
