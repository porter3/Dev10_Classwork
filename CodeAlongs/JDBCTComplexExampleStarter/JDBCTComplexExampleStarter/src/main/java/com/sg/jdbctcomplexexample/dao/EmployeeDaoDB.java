package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/* class needs to be annotated as @Repository since we're using annotation-based dependency injection\
saying DAO acts like a literal repo for data, saying it's being used for storage purposes
and it used for persistence
*/
@Repository
public class EmployeeDaoDB implements EmployeeDao{

    @Autowired  // signals to Spring that it can go into the 'propeties' file to find the info to make this
    JdbcTemplate jdbc;
    
    // made public so the Meeting DAO can access it when we want to add a list of Employees to the meeting
    // THE MAPPER RETURNS A SINGLE UDT OBJECT
    public static final class EmployeeMapper implements RowMapper<Employee>{
        @Override
        public Employee mapRow(ResultSet rs, int i) throws SQLException {
            // create new Employee object
            Employee emp = new Employee();
            // set all of its fields from the ResultSet
            emp.setId(rs.getInt("ID"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            return emp;
        }
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
        // run the query specified aboce, pass in the EmployeeMapper to map Employees to a List
        return jdbc.query(SELECT_ALL_EMPLOYEES, new EmployeeMapper());
    }

    @Override
    public Employee getEmployeeById(int id) {
        try{
            final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE ID = ?";
            return jdbc.queryForObject(SELECT_EMPLOYEE_BY_ID, new EmployeeMapper());
        }
        catch(DataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional // once again, Transactional means that all queries will be run together (all-or-nothing)
    public Employee addEmployee(Employee employee) {
        final String INSERT_EMPLOYEE = "INSERT INTO employee(firstName, lastName) VALUES(?,?)";
        jdbc.update(INSERT_EMPLOYEE, employee.getFirstName(), employee.getLastName());
        // get the new ID and assign it to Employee, specifying the type of object you're going to get back
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        employee.setId(newID);
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        final String UPDATE_EMPLOYEE = "UPDATE employee SET firstName = ?, lastName = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_EMPLOYEE, employee.getFirstName(), employee.getLastName(), employee.getId());
    }
    
    // Have to consider the relationship with Meeting
    /* Must use @Transactional b/c multiple updates are being made, 
     don't want data to be changed by someone else mid-query
    */
    @Transactional
    @Override
    public void deleteEmployeeById(int id) {
        // first have to delete Employee from bridge table
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee " + 
                "WHERE employeeID = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);
        
        final String DELETE_EMPLOYEE = "DELETE FROM employee " +
                "WHERE ID = ?";
        jdbc.update(DELETE_EMPLOYEE, id);
    }

}
