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
@SpringBootTest
public class CourseDaoTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public CourseDaoTest() {
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
     * Test of getAllCourses method, of class CourseDao.
     */
    @Test
    public void testGetAllCourses() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name 2");
        course2.setTeacher(teacher);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);
        
        List<Course> courses = courseDao.getAllCourses();
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertTrue(courses.contains(course2));
    }

    /**
     * Test of addCourse method, of class CourseDao.
     */
    @Test
    public void testAddAndGetCourse() {
        // need to create a Teacher for the Course
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // need to create a list of Students for the course
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    /**
     * Test of updateCourse method, of class CourseDao.
     */
    @Test
    public void testUpdateCourse() {
        // add a Teacher for the Course
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // create a list of Students for the Course
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        // need to get student populated with an ID, hence the calling of addStudent()
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
        
        course.setName("New Test Course Name");
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        students.add(student2);
        course.setStudents(students);
        
        courseDao.updateCourse(course);
        
        // assert the course in memory got updated
        assertNotEquals(course, fromDao);
        
        // assert the course in memory is equal to its persisted counterpart
        fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    /**
     * Test of deleteCourseById method, of class CourseDao.
     */
    @Test
    public void testDeleteCourseById() {
        // need to create a Teacher for the Course
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // need a List of Student(s) for the course
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        // ensure the course gotten from persisted storage is the same as the one in memory
        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
        
        courseDao.deleteCourseById(course.getId());
        
        fromDao = courseDao.getCourseById(course.getId());
        // assert that fromDao is null and that by extension 'course' no longer exists
        assertNull(fromDao);
    }

    /**
     * Test of getCoursesForTeacher method, of class CourseDao.
     */
    @Test
    public void testGetCoursesForTeacher() {
        // create two teachers and three courses (along with students)
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test Teacher First 2");
        teacher2.setLastName("Test Teacher Last 2");
        teacher2.setSpecialty("Test Teacher Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name");
        course2.setTeacher(teacher2);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name");
        course3.setTeacher(teacher);
        course3.setStudents(students);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForTeacher(teacher);
        assertEquals(2, courses.size()); // teacher has two courses
        assertTrue(courses.contains(course)); // teacher's courses contain 'course'
        assertFalse(courses.contains(course2)); // teacher's courses do not contain course2
        assertTrue(courses.contains(course3)); // teacher's courses contain 'course3'
    }

    /**
     * Test of getCoursesForStudent method, of class CourseDao.
     */
    @Test
    public void testGetCoursesForStudent() {
        // create a new teacher so courses can exist
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        // create two students
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        
        // first list of students has both students
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        
        // second list of students only has student2
        List<Student> students2 = new ArrayList<>();
        students2.add(student2);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course course2 = new Course();
        course2.setName("Test Course Name");
        course2.setTeacher(teacher);
        course2.setStudents(students2);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name");
        course3.setTeacher(teacher);
        course3.setStudents(students);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForStudent(student); // course list for first student
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course)); // courses belonging to 'student' contain 'course'
        assertFalse(courses.contains(course2)); // courses belonging to 'student' do not contain 'course2'
        assertTrue(courses.contains(course3)); // courses belonging to 'student' contain 'course3'
    }
    
}
