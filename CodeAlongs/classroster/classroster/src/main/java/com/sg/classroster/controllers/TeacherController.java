package com.sg.classroster.controllers;

import com.sg.classroster.data.CourseDao;
import com.sg.classroster.data.StudentDao;
import com.sg.classroster.data.TeacherDao;
import com.sg.classroster.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jake
 */

@Controller
public class TeacherController {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    @GetMapping("teachers") // goes to /teachers URL, will display teachers.html
    public String displayTeachers(Model model){ // Model is an object for sending data to the view engine
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("teachers", teachers); // adds the list 'teachers' to the ...
        return "teachers"; // returning the teachers.html file???
    }
    
    @PostMapping("addTeacher") // HTML form will send data to here in the form of an HttpServletRequest
    public String addTeacher(HttpServletRequest request){ // HttpServletRequest is part of the JavaX package
        // get all Teacher properties from the HTML form
        // getParameter() is accessing the 'name' attribute for an HTML input field
        String firstName = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
        
        // set all Teacher attributes, add them to object in memory
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastname);
        teacher.setSpecialty(specialty);
        // add new Teacher to the DB
        teacherDao.addTeacher(teacher);
        
        return "redirect:/teachers"; // will refresh'redirect to /teachers page
    }
    
    @GetMapping("deleteTeacher") /* although this is a 'delete' method, it's mapped as GET
                                    since it's working off a link */
                                    // ^ try to use DELETE mapping to see if it works < let Sean know if it doesn't work
    public String deleteTeacher(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        teacherDao.deleteTeacherById(id);
        
        return "redirect:/teachers";
    }
    
    // redirects to editTeacher.html
    @GetMapping("editTeacher")
    public String editTeacher(HttpServletRequest request, Model model){
        // get the Teacher info/id from the Request
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDao.getTeacherById(id);
        
        // display the teacher's current info by adding it to the model
        model.addAttribute("teacher", teacher);
        
        // return editTeacher.html??????
        return "editTeacher";
    }
    
    @PostMapping("editTeacher")
    public String performEditTeacher(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDao.getTeacherById(id);
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setSpecialty(request.getParameter("specialty"));
        teacherDao.updateTeacher(teacher);
        
        return "redirect:/teachers";
    }
    
    
}
