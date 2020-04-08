/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class EmployeeDaoDBTest {
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    MeetingDao meetingDao;
    
    public EmployeeDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    // delete all employees
    public void setUp() {
        System.out.println("EMPLOYEE DAO TESTS ARE RUNNING");
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees){
            employeeDao.deleteEmployeeById(employee.getId());
        }
        
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }

        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllEmployees method, of class EmployeeDao.
     */
    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);
        
        List<Employee> employees = employeeDao.getAllEmployees();
        assertTrue(employees.contains(employee));
        assertTrue(employees.contains(employee2));
        assertEquals(2, employees.size());
    }

    /**
     * Test of getEmployeeById method, of class EmployeeDao.
     */
    @Test
    public void testAddGetEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee employeeFromDB = employeeDao.getEmployeeById(employee.getId());
        
        assertEquals(employee, employeeFromDB);
    }

    /**
     * Test of updateEmployee method, of class EmployeeDao.
     */
    @Test
    public void testUpdateEmployee() {
        // create Employee and add them to DB
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        // retrieve same Employee from DB
        Employee employeeFromDB = employeeDao.getEmployeeById(employee.getId());
        // assert that the get method works
        assertEquals(employee, employeeFromDB);
        
        employee.setFirstName("Another Test First");
        
        employeeDao.updateEmployee(employee);
        // assert that the two local employees are different
        assertNotEquals(employee, employeeFromDB);
        
        // have employeeFromDB get the updated employee info
        employeeFromDB = employeeDao.getEmployeeById(employee.getId());
        assertEquals(employee, employeeFromDB);
    }

    /**
     * Test of deleteEmployeeById method, of class EmployeeDao.
     */
    @Test
    public void testDeleteEmployeeById() {
        // need a meeting and room since that employee would need to be deleted
            // from their meetings (bridge table), and a meeting requires a room
        
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        employeeDao.deleteEmployeeById(employee.getId());
        Employee employeeFromDB = employeeDao.getEmployeeById(employee.getId());
        
        // assert employee no longer exists in DB
        assertNull(employeeFromDB);
    }
    
}
