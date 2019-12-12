/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Student;
import java.util.List;

/**
 *
 * @author jake
 */

// Defines all the methods for any class we use that is a data-access object (DAO)
public interface ClassRosterDao {
    /**
     * Adds Student to roster and associates it with the given ID.
     * If there is already a student associated with the ID, it will return
     * the Student object, otherwise will return null.
     * 
     * @param studentId with which student is associated
     * @param student to be added to roster
     * @return the Student object previously associated with the ID, null otherwise
     */
    Student addStudent(String studentId, Student student);
    
    /**
     * Return a String array containing all studentIds in the roster
     * 
     * @return String array with all studentIds
     */
    List<Student> getAllStudents();
    
    /**
     * Return the student object associated w/ the given student id.
     * Return null if student doesn't exist.
     * 
     * @param studentId ID of student to retrieve
     * @return the Student object associated with the id, null if nonexistent
     */
    Student getStudent(String studentId);
    
    /**
     * Removes from the roster the student associated with the given id
     * Return the student object being removed, null if student doesn't exist
     * 
     * @param studentId if of student to be removed
     * @return Student object that was removed, null if nonexistent
     */
    Student removeStudent(String studentId);
}
