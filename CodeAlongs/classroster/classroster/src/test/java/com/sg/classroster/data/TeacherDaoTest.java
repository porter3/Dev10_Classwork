/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.data;

import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
import com.sg.classroster.entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherDaoTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public TeacherDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for (Teacher teacher : teachers){
            teacherDao.deleteTeacherById(teacher.getId());
        }
        
        List<Student> students = studentDao.getAllStudents();
        for (Student student : students){
            studentDao.deleteStudentById(student.getId());
        }
        
        List<Course> courses = courseDao.getAllCourses();
        for (Course course: courses){
            courseDao.deleteCourseById(course.getId());
        }
            
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllTeachers method, of class TeacherDao.
     */
    @Test
    public void testGetAllTeachers() {
        // create two teachers, add them to storage.
        // Assert that the list contains each teacher, as well as has a size of 2.
        
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test First 2");
        teacher2.setLastName("Test Last 2");
        teacher2.setSpecialty("Test Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);
        
        List<Teacher> teachers = teacherDao.getAllTeachers();
        
        assertEquals(2, teachers.size());
        assertTrue(teachers.contains(teacher));
        assertTrue (teachers.contains(teacher2));
    }

    /**
     * Test of addTeacher method, of class TeacherDao.
     */
    @Test
    public void testAddAndGetTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        
        assertEquals(teacher, fromDao);
    }

    /**
     * Test of updateTeacher method, of class TeacherDao.
     */
    @Test
    public void testUpdateTeacher() {
        // add Teacher to DAO
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // get Teacher back and assert it's the same as the one that was added in
        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, fromDao);
        
        teacher.setFirstName("New Test First");
        teacherDao.updateTeacher(teacher);
        
        // teacher (the one in memory) should now have a new first name
        assertNotEquals(teacher, fromDao);
        
        fromDao = teacherDao.getTeacherById(teacher.getId());
        
        // assert updateTeacher also changed the teacher in persisted storage
        assertEquals(teacher, fromDao);
    }

    /**
     * Test of deleteTeacherById method, of class TeacherDao.
     */
    @Test
    public void testDeleteTeacherById() {
        // Add teacher and all related objects to persisted storage
         Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        // add a Course with one student and the corresponding teacher
        Course course = new Course();
        course.setName("Test Course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        // get teacher/assert that they're in persisted storage
        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, fromDao);   
    
        teacherDao.deleteTeacherById(teacher.getId());
        
        fromDao = teacherDao.getTeacherById(teacher.getId());
        // assert teacher no longer exists in persisted storage
        assertNull(fromDao);
    }
    
}
