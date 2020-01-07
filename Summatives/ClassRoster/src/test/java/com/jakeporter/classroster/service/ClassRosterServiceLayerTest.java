/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.classroster.service;

import com.jakeporter.classroster.dao.ClassRosterAuditDao;
import com.jakeporter.classroster.dao.ClassRosterAuditDaoStubImpl;
import com.jakeporter.classroster.dao.ClassRosterDao;
import com.jakeporter.classroster.dao.ClassRosterDaoStubImpl;
import com.jakeporter.classroster.dto.Student;
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
public class ClassRosterServiceLayerTest {
    
    private ClassRosterServiceLayer service;
    
    public ClassRosterServiceLayerTest() {
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//        
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);

        // instantiate the context object
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // arguments for service layer's constructor are the id, and its type (ClassRosterServiceLayer class)
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
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
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    // the "happy path", everything is ok
    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("Jake");
        student.setLastName("Porter");
        student.setCohort("Java  22");
        service.createStudent(student);
    }
    
    @Test
    public void testCreateStudentDuplicateId() throws Exception{
        Student student = new Student("0001");
        student.setFirstName("Jake");
        student.setLastName("Porter");
        student.setCohort("Java 22");
        // if DuplicateIdException occurs, the test passes. Does not otherwise
        try{
            service.createStudent(student);
            // fail() is statis JUnit method
            fail("Expected ClassRosterDuplicateIdException, was not thrown");
        }
        catch(ClassRosterDuplicateIdException e){
            // just returning signals that everything is fine and test passes
            return;
        }
    }
    
    // trying and catching for invalid data
    @Test
    public void testCreateStudentInvalidData() throws Exception{
        // setting things up
        //. student ID was changed from 0001 to 0002 because the student constructed
            // by the DAO stubImpl has Id of 0001
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Porter");
        student.setCohort("Java 22");
        
        try{
            service.createStudent(student);
            // if this try block was executable, the test has failed because a student
                // with invalid data was allowed to be created
            fail("Expected ClassRosterDataValidationException, was not thrown");
        }
        catch(ClassRosterDataValidationException e){
            // test passes
            return;
        }
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
        // test that we DO get something back when we ask for Student 0001 (in the DAO),
            // test that we don't get something back when we ask for a different ID
        Student student = service.getStudent("0001");
        assertNotNull(student);
        // put in bogus ID
        student = service.getStudent("9999");
        assertNull(student);
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        // if I try to remove a student object that exists, it will return that object, null otherwise
        Student student = service.removeStudent("0001");
        assertNotNull(student);
        // put in bogus ID
        student = service.getStudent("9999");
        assertNull(student);
    }
    
}
