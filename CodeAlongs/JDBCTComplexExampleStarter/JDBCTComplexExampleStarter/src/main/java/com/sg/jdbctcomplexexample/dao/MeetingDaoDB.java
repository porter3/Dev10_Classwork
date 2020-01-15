package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.dao.EmployeeDaoDB.EmployeeMapper;
import com.sg.jdbctcomplexexample.dao.RoomDaoDB.RoomMapper;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jake
 */

// Most complicated DAO - handles relationship with the Room and Employee

@Repository
public class MeetingDaoDB implements MeetingDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    // create MeetingMapper
    public static final class MeetingMapper implements RowMapper<Meeting>{

        @Override
        public Meeting mapRow(ResultSet rs, int i) throws SQLException {
            // instantiate new UDT
            Meeting meeting = new Meeting();
            // populate it
            meeting.setId(rs.getInt("id"));
            meeting.setName(rs.getString("name"));
            meeting.setTime(rs.getTimestamp("time").toLocalDateTime());
            return meeting;
        }
    }

    @Override
    public List<Meeting> getAllMeetings() {
        final String SELECT_ALL_MEETINGS = "SELECT * FROM meeting";
        List<Meeting> meetings = jdbc.query(SELECT_ALL_MEETINGS, new MeetingMapper());
        
        // populate all the meetings with their room data and attendee data
        addRoomAndEmployeesToMeetings(meetings);
        return meetings;
    }

    // wrap in try-catch block in case queryForMeeting does not find the Meeting
    @Override
    public Meeting getMeetingByid(int id) {
        try{
            final String SELECT_MEETING_BY_ID = "SELECT * FROM meeting WHERE id = ?";
            // create a new Meeting object, populate it with its fields
            Meeting meeting = jdbc.queryForObject(SELECT_MEETING_BY_ID, new MeetingMapper(), id);
            // populate the Meeting object with the bridge table fields (its Room and Attendees/Employees)
            meeting.setRoom(getRoomForMeeting(meeting));    // bridge table helper method
            meeting.setAttendees(getEmployeesForMeeting(meeting)); // bridge table helper method
            return meeting;
        }
        catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public Meeting addMeeting(Meeting meeting) {
        final String INSERT_MEETING = "INSERT INTO meeting(name, time, roomId) VALUES(?,?,?)";
        jdbc.update(INSERT_MEETING, meeting.getName(), Timestamp.valueOf(meeting.getTime()), meeting.getRoom().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        meeting.setId(newId);
        
        // bridge table helper method
        insertMeetingEmployee(meeting);
        return meeting;
    }

    @Override
    @Transactional // making multiple calls to the DB that will modify it
    public void updateMeeting(Meeting meeting) {
        // Update the Meeting table's information
        final String UPDATE_MEETING = "UPDATE meeting SET "
                + "name = ?, time = ?, roomID = ? WHERE ID  = ?";
        // Timestamp is a wrapper class for Dates in Java
        jdbc.update(UPDATE_MEETING, meeting.getName(), Timestamp.valueOf(meeting.getTime()), meeting.getRoom().getId(), meeting.getId());
        
        // Update the meeting_employee table's info, as the meeting may now have different attendees
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingID + ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, meeting.getId());
        insertMeetingEmployee(meeting);
    }

    @Override
    public void deleteMeetingById(int id) {
        // gotta get rid of the bridge table entries first
        final String DELETE_MEETING_EMPLOYEE= "DELETE FROM meeting_employee "
                + "WHERE meetingID = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);
        
        // delete the actual meeting
        final String DELETE_MEETING = "DELETE FROM meeting WHERE id = ?";
        jdbc.update(DELETE_MEETING, id);
    }

    @Override
    public List<Meeting> getMeetingsForRoom(Room room) {
        final String SELECT_MEETINGS_FOR_ROOM = "SELECT * FROM meeting WHERE roomId = ?";
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_ROOM, new MeetingMapper(), room.getId());
        
        addRoomAndEmployeesToMeetings(meetings);
        return meetings;
    }
    

    @Override
    public List<Meeting> getMeetingsForEmployee(Employee employee) {
        final String SELECT_MEETINGS_FOR_EMPLOYEE = "SELECT * FROM meeting m " 
                + "INNER JOIN meeting_employee me ON m.id = me.meetingId WHERE me.employeeId = ?";
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_EMPLOYEE, new MeetingMapper(), employee.getId());
        addRoomAndEmployeesToMeetings(meetings);
        return meetings;
    }

    
    // HELPER METHODS
    
    private Room getRoomForMeeting(Meeting meeting){
        final String SELECT_ROOM_FOR_MEETING = "SElECT r.* FROM room r "
                + "INNER JOIN meeting m ON r.id = m.roomId WHERE m.id = ?";
        /* Notice this is NOT in a try-catch block-
        Doesn't need to be since it's a helper method. The meeting getting queried for
        has already been been proven to exist if this method is getting executed.
        */
        return jdbc.queryForObject(SELECT_ROOM_FOR_MEETING, new RoomMapper(), meeting.getId());
    }
    
    private List<Employee> getEmployeesForMeeting(Meeting meeting){
        final String SELECT_EMPLOYEES_FOR_MEETING = "SELECT e.* FROM employee e "
                + "INNER JOIN meeting_employee me ON e.ID = me.employeeID WHERE me.meetingID = ?";
        return jdbc.query(SELECT_EMPLOYEES_FOR_MEETING, new EmployeeMapper(), meeting.getId());
    }
    
    // Doesn't need to return anything? Thought it was getting a copy of the reference being passed in.
    private void addRoomAndEmployeesToMeetings(List<Meeting> meetings){
        for (Meeting meeting : meetings){
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
        }
    }
    
    private void insertMeetingEmployee(Meeting meeting){
        // create query for inserting values into bridge table
        final String INSERT_MEETING_EMPLOYEE = "INSERT INTO meeting_employee"
                + "(meetingID, employeeID) VALUES (?,?)";
        // loop over the list of Employees in the Meeting object, update the bridge table
        for(Employee employee : meeting.getAttendees()){
            jdbc.update(INSERT_MEETING_EMPLOYEE, meeting.getId(), employee.getId());
        }
    }
}
