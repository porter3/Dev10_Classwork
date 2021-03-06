package com.sg.classroster.controllers;

import com.sg.classroster.data.CourseDao;
import com.sg.classroster.data.StudentDao;
import com.sg.classroster.data.TeacherDao;
import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
import com.sg.classroster.entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jake
 */

@Controller
public class CourseController {

    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    // will render courses.html
    @GetMapping("courses")
    public String displayCourses(Model model){
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("courseList", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "courses";
    }
    
    // get info for new course to add, create a Course object with it, add it to DB
    @PostMapping("addCourse")
    // HttpServletRequest is being passed in to hold the teacher ID and student IDs
    public String addCourse(Course course, HttpServletRequest request){
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");
        
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        List<Student> students = new ArrayList();
        // add students to the list 'students' by grabbing them by their IDs from the DB
        for(String studentId : studentIds){
            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
        }
        // assign the list of students to the course
        course.setStudents(students);
        courseDao.addCourse(course);
        
        return "redirect:/courses";
    }
    
    // renders courseDetail.html
    @GetMapping("courseDetail")
    public String courseDetail(Integer id, Model model){
        // get the Course 
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }
    
    @GetMapping("deleteCourse")
    public String deleteCourse(Integer id){
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }
    
    @GetMapping("editCourse")
    public String editCourse(Integer id, Model model){
        // add Course to the Model for editing
        Course course = courseDao.getCourseById(id);
        List<Student> students = studentDao.getAllStudents();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("course", course);
        model.addAttribute("studentList", students);
        model.addAttribute("teacherList", teachers);
        return "editCourse";
    }
    
    // Going to validate that at least one Student exists in the Course's list of Students
    @PostMapping("editCourse")
    public String performEditCourse(@Valid Course course, BindingResult result, HttpServletRequest request, Model model){
        String teacherId = request.getParameter("teacherId");
        // multiple students can be selected in the input, but the 'name' attribute of that input is "studentId"
        String[] studentIds = request.getParameterValues("studentId");
        
        // take the Course being passed in and set its Teacher
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        List<Student> studentList = new ArrayList();
        if(studentIds != null){
            // look up each Student by their ID in the DB and add them to the list of Students
            for(String studentId : studentIds){
                studentList.add(studentDao.getStudentById(Integer.parseInt(studentId)));
            }
        }
        else{ // BindingResult uses FieldError to keep track of which field has errors in the project
            FieldError error = new FieldError("course", "students", "Must include one student"); // <- what exactly is the first argument here?
            result.addError(error); // add the FieldError to the BindingResult
        }
        
        course.setStudents(studentList);
        
        // if the Course object had some validation errors, redirect to /editCourse with all its current fields populated
        if(result.hasErrors()){
            model.addAttribute("teachers", teacherDao.getAllTeachers());
            model.addAttribute("students", studentDao.getAllStudents());
            model.addAttribute("course", course);
            return "editCourse";
        }
        
        // otherwise, update the course
        courseDao.updateCourse(course);
        
        return "redirect:/courses";
    }
    
}
