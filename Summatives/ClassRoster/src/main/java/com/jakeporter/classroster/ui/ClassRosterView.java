package com.jakeporter.classroster.ui;

import com.jakeporter.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author jake
 */

public class ClassRosterView {
    
    UserIO io;
    
    // making the user interface modular
    public ClassRosterView(UserIO io){
        this.io = io;
    }

        public int printMenuAndGetSelection(){
            io.print("Main Menu");
            io.print("1. List Student IDs");
            io.print("2. Create New Student");
            io.print("3. View a Student");
            io.print("4. Remove a Student");
            io.print("5. Exit");
        
        
        return io.readInt("Please select from the above choices:  ", 1, 5);
        }
        
        public Student getNewStudentInfo(){
            String studentId = io.readString("Please enter Student ID");
            String firstName = io.readString("Please enter first name");
            String lastName = io.readString("Please enter last name");
            String cohort = io.readString("Please enter Cohort");
            Student currentStudent = new Student(studentId);
            currentStudent.setFirstName(firstName);
            currentStudent.setLastName(lastName);
            currentStudent.setCohort(cohort);
            return currentStudent;
        }
        
        public void displayCreateStudentbanner(){
            io.print("=== Create Student ===");
        }
        
        public void displayCreateSuccessBanner(){
            io.readString("Student successfully created. Please hit enter to continue");
        }
        
        public void displayStudentList(List<Student> studentList){
            for (Student currentStudent : studentList){
                io.print(currentStudent.getStudentId() + ": " + 
                        currentStudent.getFirstName() + " " + 
                        currentStudent.getLastName());
            }
            // readString returns the value of Scanner class's nextLine() method
            io.readString("Please hit enter to continue.");
        }
        
        public void displayDisplayAllBanner(){
            io.print("=== Display All Students ===");
        }
        
        public void displayDisplayStudentBanner(){
            io.print("=== Display Student +++");
        }
        
        public String getStudentIdChoice(){
            return io.readString("Please enter the Student ID.");
        }
        
        public void displayStudent(Student student){
            if (student != null){
                io.print(student.getStudentId());
                io.print(student.getFirstName() + " " + student.getLastName());
                io.print(student.getCohort());
                io.print("");
            }
            else{
                io.print("No such student.");
            }
            io.readString("Hit enter to continue.");
        }
        
        public void displayRemoveStudentBanner(){
            io.print("=== Remove Student ===");
        }
        
        public void displayRemoveSuccessBanner(){
            io.readString("Student successfully removed. Please hit enter to continue.");
        }
        
        public void displayRemoveFailureBanner(){
            io.print("Selected student does not exist");
        }
        
        public void displayExitBanner(){
            io.print("Goodbye!");
        }
        
        public void displayUnknownCommandBanner(){
            io.print("Unknown command.");
        }
        
        public void displayErrorMessage(String errorMsg){
            io.print("--ERROR--");
            io.print(errorMsg);
        }
        
}
