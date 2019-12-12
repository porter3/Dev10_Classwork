package controller;

import dao.ClassRosterDao;
import dao.ClassRosterDaoFileImpl;
import dto.Student;
import java.util.List;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author jake
 */

// This orchestrates the application. Will be composed of the view and data access objects
public class ClassRosterController {

    ClassRosterDao dao;
    ClassRosterView view;
    
    // objects being passed are instantiated in main()
    // View object is being passed in so its dependency (UserIO) is injected into ITS OWN constructor.
        // Don't want to instantiate that dependecy in the controller, that would not be loose coupling.
        // Also, want to make "view" modular if I wanted to create different menu options/GUI/something else
    public ClassRosterController(ClassRosterDao dao, ClassRosterView view){
        this.dao = dao;
        this.view = view;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
            
            menuSelection = getMenuSelection();
        
        switch(menuSelection){
            case 1:
                listStudents();
                break;
            case 2:
                createStudent();
                break;
            case 3:
                viewStudent();
                break;
            case 4:
                removeStudent();
                break;
            case 5:
                keepGoing = false;
                break;
            default:
                unknownCommand();
        }
        }
    exitMessage();
    
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent(){
        view.displayCreateStudentbanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.dislpayCreateSuccessBanner();
    }
    
    private void listStudents(){
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent(){
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent(){
        // display banner
        view.displayRemoveStudentBanner();
        // get key for student to remove
        String studentId = view.getStudentIdChoice();
        // use data access object to remove student
        dao.removeStudent(studentId);
        // display success banner
        view.displayRemoveSuccessBanner();
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
}
