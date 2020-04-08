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
public class MeetingDaoDBTest {
    
    @Autowired
    RoomDao roomDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    MeetingDao meetingDao;
    
    public MeetingDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("MEETING DAO TESTS ARE RUNNING");
        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings){
            meetingDao.deleteMeetingById(meeting.getId());
        }
        
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }

        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllMeetings method, of class MeetingDao.
     */
    @Test
    public void testGetAllMeetings() {
        // create a Room and Emplyees for the Meeting
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);

        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        // need to make a List of attendees for setAttendees() below
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0));
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        // make 2nd Meeting with same Room and Employees
        Meeting meeting2 = new Meeting();
        meeting2.setName("Test Meeting 2");
        meeting2.setTime(LocalDateTime.now().withNano(0));
        meeting2.setRoom(room);
        meeting2.setAttendees(employees);
        meeting2 = meetingDao.addMeeting(meeting2);
        
        List<Meeting> meetings = meetingDao.getAllMeetings();
        
        assertEquals(2, meetings.size());
        assertTrue(meetings.contains(meeting));
        assertTrue(meetings.contains(meeting2));
    }

    /**
     * Test of getMeetingByid method, of class MeetingDao.
     */
    @Test
    public void testAddGetMeeting() {
        // create new Room for the Meeting
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);

        // create dummy Employee to put in the Meeting
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        // need a list of employees for Meeting's setAttendees() method
        List<Employee> employees = new ArrayList();
        employees.add(employee);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0)); /* withNano() is called
        because LocalDateTime tracks nanoseconds and the DB does not. Setting that value to 0
        ensures meeting's time will match what comes out of the database.
        */
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        Meeting meetingFromDB = meetingDao.getMeetingByid(meeting.getId());
        
        assertEquals(meeting, meetingFromDB);
    }

    /**
     * Test of updateMeeting method, of class MeetingDao.
     */
    @Test
    public void testUpdateMeeting() {
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);

        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0));
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        Meeting meetingFromDB = meetingDao.getMeetingByid(meeting.getId());
        
        assertEquals(meeting, meetingFromDB); // ensure everything works properly
        
        meeting.setName("Not original meeting name");
        
        // create/add 2nd employee to add into meeting
        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);
        
        employees.add(employee2);
        
        meeting.setAttendees(employees);
        
        // update the meeting in DB with the one changed in memory
        meetingDao.updateMeeting(meeting);
        
        assertNotEquals(meeting, meetingFromDB); // ensure meeting in memory was actually changed
        
        meetingFromDB = meetingDao.getMeetingByid(meeting.getId());
        
        assertEquals(meeting, meetingFromDB);
    }

    /**
     * Test of deleteMeetingById method, of class MeetingDao.
     */
    @Test
    public void testDeleteMeetingById() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0));
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        meetingDao.deleteMeetingById(meeting.getId());
        
        Meeting meetingFromDB = meetingDao.getMeetingByid(meeting.getId());
        
        // assert you got nothing back from getMeetingById()
        assertNull(meetingFromDB);
    }

    /**
     * Test of getMeetingsForRoom method, of class MeetingDao.
     */
    @Test
    public void testGetMeetingsForRoom() {
        // create multiple rooms and multiple meetings, ensure you're only getting back the meetings for a specific room
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Room room2 = new Room();
        room2.setName("Test Room 2");
        room2.setDescription("Test Room Description 2");
        room2 = roomDao.addRoom(room2);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0));
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        Meeting meeting2 = new Meeting();
        meeting2.setName("Test Meeting");
        meeting2.setTime(LocalDateTime.now().withNano(0));
        meeting2.setRoom(room2);
        meeting2.setAttendees(employees);
        meeting2 = meetingDao.addMeeting(meeting2);
        
        Meeting meeting3 = new Meeting();
        meeting3.setName("Test Meeting");
        meeting3.setTime(LocalDateTime.now().withNano(0));
        meeting3.setRoom(room);
        meeting3.setAttendees(employees);
        meeting3 = meetingDao.addMeeting(meeting3);
 
        List<Meeting> meetingsForRoom = meetingDao.getMeetingsForRoom(room);
        
        // assert that meetings has a certain size, contains two of these and doesn't contain the other
        assertEquals(2, meetingsForRoom.size());
        assertTrue(meetingsForRoom.contains(meeting));
        assertTrue(meetingsForRoom.contains(meeting3));
        assertFalse(meetingsForRoom.contains(meeting2));
    }

    /**
     * Test of getMeetingsForEmployee method, of class MeetingDao.
     */
    @Test
    public void testGetMeetingsForEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);

        // create two lists of attendees, one without employee
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);
        
        List<Employee> employees2 = new ArrayList<>();
        employees2.add(employee2);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        // create multiple meetings, one of which does not have employee in their attendees list
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now().withNano(0));
        meeting.setRoom(room);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        Meeting meeting2 = new Meeting();
        meeting2.setName("Test Meeting");
        meeting2.setTime(LocalDateTime.now().withNano(0));
        meeting2.setRoom(room);
        meeting2.setAttendees(employees2);
        meeting2 = meetingDao.addMeeting(meeting2);
        
        Meeting meeting3 = new Meeting();
        meeting3.setName("Test Meeting");
        meeting3.setTime(LocalDateTime.now().withNano(0));
        meeting3.setRoom(room);
        meeting3.setAttendees(employees);
        meeting3 = meetingDao.addMeeting(meeting3);
        
        List<Meeting> meetingsForEmployee = meetingDao.getMeetingsForEmployee(employee);
        
        assertEquals(2, meetingsForEmployee.size());
        assertTrue(meetingsForEmployee.contains(meeting));
        assertTrue(meetingsForEmployee.contains(meeting3));
        assertFalse(meetingsForEmployee.contains(meeting2));
    }
    
}
