package com.jakeporter.classroster.service;

import dao.ClassRosterAuditDao;
import dao.ClassRosterDao;
import dao.ClassRosterPersistenceException;
import dto.Student;
import java.util.List;

/**
 *
 * @author jake
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer{
    
    private ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        // check if student Id already exists
        if (dao.getStudent(student.getStudentId()) != null){
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student, student ID" + student.getStudentId() + " already exists.");
        }
        // if new ID is exclusive, validate the rest of the user-provided data. Throw validation exception if it's not what we want.
        validateStudentData(student);
        
        // persist the student object
        dao.addStudent(student.getStudentId(), student);
        
        // NEW METHOD: write to the audit log
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED");
    }

    // pass-through method
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    // pass-through method
    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    // at the moment, is pass-through method until adding the audit-logging functionality
    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent =  dao.removeStudent(studentId);
        // Write to audit log
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED");
        return removedStudent;
    }

    private void validateStudentData(Student student) throws ClassRosterDataValidationException{
        if (student.getFirstName() == null
                // .trim() removes leading/trailing whitespace to ensure the user didn't just enter some empty space
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0){
            
            throw new ClassRosterDataValidationException("ERROR: All fields (first name/last name/cohort are required.");
        }
    }
}
