package com.jakeporter.classroster.dao;

import com.jakeporter.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jake
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao{
    
    Student onlyStudent;
    List<Student> listOfStudents = new ArrayList();
    
    public ClassRosterDaoStubImpl(){
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Joe");
        onlyStudent.setLastName("Murray");
        onlyStudent.setCohort("Java 18");
        listOfStudents.add(onlyStudent);
    }

    // WHAT I DON'T UNDERSTAND YET: how it's ok to have these stubbed DAO methods
    // making it so that you won't duplicate objects with the same ID
    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return listOfStudents;
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }

}
