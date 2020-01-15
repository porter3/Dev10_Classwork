/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jake
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MeetingDaoDBTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllMeetings method, of class MeetingDao.
     */
    @Test
    public void testGetAllMeetings() {
    }

    /**
     * Test of getMeetingByid method, of class MeetingDao.
     */
    @Test
    public void testGetMeetingByid() {
    }

    /**
     * Test of addMeeting method, of class MeetingDao.
     */
    @Test
    public void testAddMeeting() {
    }

    /**
     * Test of updateMeeting method, of class MeetingDao.
     */
    @Test
    public void testUpdateMeeting() {
    }

    /**
     * Test of deleteMeetingById method, of class MeetingDao.
     */
    @Test
    public void testDeleteMeetingById() {
    }

    /**
     * Test of getMeetingsForRoom method, of class MeetingDao.
     */
    @Test
    public void testGetMeetingsForRoom() {
    }

    /**
     * Test of getMeetingsForEmployee method, of class MeetingDao.
     */
    @Test
    public void testGetMeetingsForEmployee() {
    }

    public class MeetingDaoImpl implements MeetingDao {

        public List<Meeting> getAllMeetings() {
            return null;
        }

        public Meeting getMeetingByid(int id) {
            return null;
        }

        public Meeting addMeeting(Meeting meeting) {
            return null;
        }

        public void updateMeeting(Meeting meeting) {
        }

        public void deleteMeetingById(int id) {
        }

        public List<Meeting> getMeetingsForRoom(Room room) {
            return null;
        }

        public List<Meeting> getMeetingsForEmployee(Employee employee) {
            return null;
        }
    }
    
}
