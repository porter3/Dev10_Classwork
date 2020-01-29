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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

// @RunWith is a JUnit annotation
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentDaoTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public StudentDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for(Teacher teacher : teachers) {
            teacherDao.deleteTeacherById(teacher.getId());
        }
        
        List<Student> students = studentDao.getAllStudents();
        for(Student student : students) {
            studentDao.deleteStudentById(student.getId());
        }
        
        List<Course> courses = courseDao.getAllCourses();
        for(Course course : courses) {
            courseDao.deleteCourseById(course.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllStudents method, of class StudentDao.
     */
    @Test
    public void testGetAllStudents() {
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        
        List<Student> students = studentDao.getAllStudents();
        
        assertEquals(2, students.size());
        assertTrue(students.contains(student));
        assertTrue(students.contains(student2));
    }

    /**
     * Test of addStudent method, of class StudentDao.
     */
    @Test
    public void testAddAndGetStudent() {
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);
    }

    /**
     * Test of updateStudent method, of class StudentDao.
     */
    @Test
    public void testUpdateStudent() {
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);
        
        student.setFirstName("New Test Student First");
        studentDao.updateStudent(student);
        
        assertNotEquals(student, fromDao);
        
        fromDao = studentDao.getStudentById(student.getId());
        
        assertEquals(student, fromDao);
    }

    /**
     * Test of deleteStudentById method, of class StudentDao.
     */
    @Test
    public void testDeleteStudentById() {
        
        // create a new Teacher for a course the student will be added to
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // create a Student, adding them to a List so they can be added to a course
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        // create a new Course, adding the Student in to test their removal
        Course course = new Course();
        course.setName("Test Course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);
        
        studentDao.deleteStudentById(student.getId());
        fromDao = studentDao.getStudentById(student.getId());

        assertNull(fromDao);
    }
    
}
