/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.addressbook.dao;

import com.jakeporter.addressbook.dto.Address;
import com.jakeporter.addressbook.dto.Person;
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
// ARRANGE, ACT, ASSERT

public class AddressBookDaoTest {
    
    AddressBookDao dao = new AddressBookDaoImpl();
    
    public AddressBookDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws Exception{
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        List<Person>personList = dao.getAllPersons();
        System.out.println("BEFORE: " + personList.size());
        for (Person person : personList){
            dao.removePerson(person.getLastName());
        }
        System.out.println("AFTER: " + personList.size());
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addPerson and getPerson method, of class AddressBookDao.
     */
    // testing both methods at the same time since we need to get an object back
        // to verify it was really added 
    @Test
    public void testAddGetPerson() throws Exception {
        Address jakeAddress = new Address();
        jakeAddress.setStreet("77 Race St");
        jakeAddress.setCity("Pittstown");
        jakeAddress.setState("NJ");
        jakeAddress.setZip("08867");
        Person jake = new Person();
        jake.setFirstName("Jake");
        jake.setLastName("Porter");
        jake.setAddress(jakeAddress);
        
        dao.addPerson(jake.getLastName(), jake);
        
        Person fromDao = dao.findPerson(jake.getLastName());
        System.out.println("PRINT TEST");
        // testing that we added a person and that the object our finding/getting method
            // returned is the same, meaning both methods work
        assertEquals(jake, fromDao);
    }

    /**
     * Test of removePerson method, of class AddressBookDao.
     */
    @Test
    public void testRemovePerson() throws Exception {
        Address jakeAddress = new Address();
        jakeAddress.setStreet("77 Race St");
        jakeAddress.setCity("Pittstown");
        jakeAddress.setState("NJ");
        jakeAddress.setZip("08867");
        Person jake = new Person();
        jake.setFirstName("Jake");
        jake.setLastName("Porter");
        jake.setAddress(jakeAddress);
        
        dao.addPerson(jake.getLastName(), jake);
        
        Address bobAddress = new Address();
        bobAddress.setStreet("random Rd");
        bobAddress.setCity("random town");
        bobAddress.setState("NC");
        bobAddress.setZip("99999");
        Person bob = new Person();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setAddress(bobAddress);
        
        dao.addPerson(bob.getLastName(), bob);
        
        dao.removePerson(jake.getLastName());
        assertEquals(1, dao.getAllPersons().size());
        assertNull(dao.findPerson(jake.getLastName()));
        
        dao.removePerson(bob.getLastName());
        assertEquals(0, dao.getAllPersons().size());
        assertNull(dao.findPerson(bob.getLastName()));
    }

    /**
     * Test of findPerson method, of class AddressBookDao.
     */
    @Test
    public void testFindPerson() throws Exception {
        
        // create person
        
        // get person and assert they are not null
    }

    /**
     * Test of getPersonCount method, of class AddressBookDao.
     */
    @Test
    public void testGetPersonCount() throws Exception {
        
        // assert the DAO size is 0
        assertEquals(0, dao.getPersonCount());
        
        // add person
        Address jakeAddress = new Address();
        jakeAddress.setStreet("77 Race St");
        jakeAddress.setCity("Pittstown");
        jakeAddress.setState("NJ");
        jakeAddress.setZip("08867");
        Person jake = new Person();
        jake.setFirstName("Jake");
        jake.setLastName("Porter");
        jake.setAddress(jakeAddress);
        dao.addPerson(jake.getLastName(), jake);
        
        assertEquals(1, dao.getPersonCount());
        
        Address bobAddress = new Address();
        bobAddress.setStreet("random Rd");
        bobAddress.setCity("random town");
        bobAddress.setState("NC");
        bobAddress.setZip("99999");
        Person bob = new Person();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setAddress(bobAddress);
        dao.addPerson(bob.getLastName(), bob);
        
        assertEquals(2, dao.getPersonCount());
    }

    /**
     * Test of getAllPersons method, of class AddressBookDao.
     */
    @Test
    public void testGetAllPersons() throws Exception {
        Address jakeAddress = new Address();
        jakeAddress.setStreet("77 Race St");
        jakeAddress.setCity("Pittstown");
        jakeAddress.setState("NJ");
        jakeAddress.setZip("08867");
        Person jake = new Person();
        jake.setFirstName("Jake");
        jake.setLastName("Porter");
        jake.setAddress(jakeAddress);
        
        dao.addPerson(jake.getLastName(), jake);
        
        Address bobAddress = new Address();
        bobAddress.setStreet("random Rd");
        bobAddress.setCity("random town");
        bobAddress.setState("NC");
        bobAddress.setZip("99999");
        Person bob = new Person();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setAddress(bobAddress);
        
        dao.addPerson(bob.getLastName(), bob);
        
        assertEquals(2, dao.getAllPersons().size());
    }
}
