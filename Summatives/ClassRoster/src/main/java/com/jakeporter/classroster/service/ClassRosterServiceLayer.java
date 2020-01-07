package com.jakeporter.classroster.service;

import com.jakeporter.classroster.dao.ClassRosterPersistenceException;
import com.jakeporter.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author jake
 */
public interface ClassRosterServiceLayer {
    
    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws ClassRosterPersistenceException;
    
    Student getStudent(String studentId) throws ClassRosterPersistenceException;
    
    Student removeStudent(String studentId) throws ClassRosterPersistenceException;
    
    
}
