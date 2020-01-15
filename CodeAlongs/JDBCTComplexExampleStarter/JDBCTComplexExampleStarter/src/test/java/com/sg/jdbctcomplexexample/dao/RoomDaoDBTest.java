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
public class RoomDaoDBTest {
    
    
    // need all three DAOs so we can create complex DTOs
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    MeetingDao meetingDao;
    
    public RoomDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /* empty the database before each test. Obviously, the getAll()
    and delete methods better work */
    @Before
    public void setUp() {
        System.out.println("THIS IS ACTUALLY RUNNING");
        
        // get all the rooms in the database
        List<Room> rooms = roomDao.getAllRooms();
        // delete each room
        for (Room room : rooms){
            roomDao.deleteRoomById(room.getId());
        }
        
        List<Employee> employees = employeeDao.getAllEmployees();
        for(Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }
        
        List<Meeting> meetings = meetingDao.getAllMeetings();
        for(Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllRooms method, of class RoomDao.
     */
    @Test
    public void testGetAllRooms() {
        // create/add some rooms, test that you get back the right amount'
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        roomDao.addRoom(room);
        
        Room room2 = new Room();
        room2.setName("Test Room 2");
        room2.setDescription("Test Room 2");
        roomDao.addRoom(room2);
        
        List<Room> rooms = roomDao.getAllRooms();
        
        // assert that getAllRooms returned 2 rooms, and that they're room and room2 specifically
        assertEquals(2, rooms.size());
        assertTrue(rooms.contains(room));
        assertTrue(rooms.contains(room2));
    }

    /**
     * Test of getRoomById method, of class RoomDao.
     */
    @Test
    public void testAddGetRoomById() {
        // create a new room and add it to DB
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        // get the room from DB
        Room fromDao = roomDao.getRoomById(room.getId());
        
        assertEquals(room, fromDao);
    }

    /**
     * Test of updateRoom method, of class RoomDao.
     */
    @Test
    public void testUpdateRoom() {
        // create room and add it to DB
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        // pull room back out from DB
        Room fromDao = roomDao.getRoomById(room.getId());
        
        assertEquals(room, fromDao);
        
        room.setName("Not the original test room description");
        
        // exchanges the room info in the DB with the room we just updated in memory
        roomDao.updateRoom(room); // room is now updated in DB
        // verify the updated one in memory and the original are different
        assertNotEquals(room, fromDao);
        
        fromDao = roomDao.getRoomById(room.getId()); // change fromDao to the updated in-DB Room
        assertEquals(room, fromDao); // assert that the local Room object is equal to the one from the DB
    }

    /**
     * Test of deleteRoomById method, of class RoomDao.
     */
    @Test
    public void testDeleteRoomById() {
        // if we delete Room, we have to delete all meetings and attendees associated with that room.
            // need to test that relationship
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        // create new attendee (represented in bridge table)
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        roomDao.deleteRoomById(room.getId());
        Room roomFromDao = roomDao.getRoomById(room.getId());
        assertNull(roomFromDao);
    }
}
