package com.sg.classroster.data;

import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
import com.sg.classroster.entities.Teacher;
import java.util.List;


/**
 *
 * @author jake
 */

// DAO for the managing DTO

public interface CourseDao {

    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);
    
    
    // note the extra methods since this is the managing class DAO
    List<Course> getCoursesForTeacher(Teacher teacher);
    List<Course> getCoursesForStudent(Student student);
}
