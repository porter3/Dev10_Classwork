package com.sg.classroster.controllers;

import com.sg.classroster.data.CourseDao;
import com.sg.classroster.data.StudentDao;
import com.sg.classroster.data.TeacherDao;
import com.sg.classroster.entities.Student;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jake
 */

@Controller
public class StudentController {

   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;
   
   @GetMapping("students")
   public String displayStudents(Model model){
       List students = studentDao.getAllStudents();
       model.addAttribute("students", students);
       return "students";
   }
   
   @PostMapping("addStudent")
   // passing in form inputs (by 'name' attribute) directly into the parameter list
   public String addStudent(String firstName, String lastName){

       Student student = new Student();
       student.setFirstName(firstName);
       student.setLastName(lastName);
       studentDao.addStudent(student);
       
       return "redirect:/students";
   }
   
    @GetMapping("deleteStudent")
    // Integer is used instead of int because it can accept a 'null' value
    public String deleteStudent(Integer id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
    
    // create the edit form for editing a student
    @GetMapping("editStudent")
    public String editStudent(Integer id, Model model){
        // pass in Student's data to display
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        
        // return editStudent.html
        return "editStudent";
    }
    
    // when the editStudent form is submitted:
    /*
    The reason this can take a Student object is because the names in the form inputs
    match up exactly with the field names in the Student object
    */
    @PostMapping("editStudent")
    // @Valid indicates Student should be validated. BindingResult holds the results of the validation.
    public String performEditStudent(@Valid Student student, BindingResult result){  /* since the PostMapping takes the full Student DTO,
                                                                                        I don't need to create a Validator to validate the data*/
        
        /* if the BindingResult picks up on Student validation errors,
            redirect the page to its original state */
        if(result.hasErrors()){     
            return "editStudent";
        }
        // otherwise, update the student and go back to the main page
        studentDao.updateStudent(student);
        return "redirect:/students";
    }
    
    /*
    CAN PULL IN DATA THREE SEPARATE WAYS:
    -Getting the individual parameters from a HttpServletRequest
    -As individual method parameters
    -Wrapped in a DTO that's passed in
    */
}
