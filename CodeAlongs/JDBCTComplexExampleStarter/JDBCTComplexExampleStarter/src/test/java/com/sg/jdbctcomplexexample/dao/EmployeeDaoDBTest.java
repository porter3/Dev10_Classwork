/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
public class EmployeeDaoDBTest {
    
    public EmployeeDaoDBTest() {
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
     * Test of getAllEmployees method, of class EmployeeDao.
     */
    @Test
    public void testGetAllEmployees() {
    }

    /**
     * Test of getEmployeeById method, of class EmployeeDao.
     */
    @Test
    public void testGetEmployeeById() {
    }

    /**
     * Test of addEmployee method, of class EmployeeDao.
     */
    @Test
    public void testAddEmployee() {
    }

    /**
     * Test of updateEmployee method, of class EmployeeDao.
     */
    @Test
    public void testUpdateEmployee() {
    }

    /**
     * Test of deleteEmployeeById method, of class EmployeeDao.
     */
    @Test
    public void testDeleteEmployeeById() {
    }

    public class EmployeeDaoImpl implements EmployeeDao {

        public List<Employee> getAllEmployees() {
            return null;
        }

        public Employee getEmployeeById(int id) {
            return null;
        }

        public Employee addEmployee(Employee employee) {
            return null;
        }

        public void updateEmployee(Employee employee) {
        }

        public void deleteEmployeeById(int id) {
        }
    }
    
}
