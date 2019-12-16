package controller;

import com.jakeporter.classroster.service.ClassRosterDataValidationException;
import com.jakeporter.classroster.service.ClassRosterDuplicateIdException;
import com.jakeporter.classroster.service.ClassRosterServiceLayer;
import dao.ClassRosterPersistenceException;
import dto.Student;
import java.util.List;
import ui.ClassRosterView;


/**
 *
 * @author jake
 */

// This orchestrates the application. Will be composed of the view and data access objects
public class ClassRosterController {

    private ClassRosterServiceLayer service;
    private ClassRosterView view;
    
    // objects being passed are instantiated in main()
    // View object is being passed in so its dependency (UserIO) is injected into ITS OWN constructor.
        // Don't want to instantiate that dependecy in the controller, that would not be loose coupling.
        // Also, want to make "view" modular if I wanted to create different menu options/GUI/something else
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view){
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
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
        catch (ClassRosterPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterPersistenceException{
        view.displayCreateStudentbanner();
        boolean hasErrors = false;
        do{
            try{
                Student newStudent = view.getNewStudentInfo();
                service.createStudent(newStudent);
                hasErrors = false;
                view.displayCreateSuccessBanner();
            }
            // single "|" operator is an "or" operator, just makes sure both expressions are evaluated
            catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        }
        while(hasErrors);
    }
    
    private void listStudents() throws ClassRosterPersistenceException{
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent() throws ClassRosterPersistenceException{
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException{
        // display banner
        view.displayRemoveStudentBanner();
        // get key for student to remove
        String studentId = view.getStudentIdChoice();
        // use data access object to remove student
        Student removedStudent = service.removeStudent(studentId);
        if (removedStudent == null){
            view.displayRemoveFailureBanner();
        }
        else{
            // display success banner
            view.displayRemoveSuccessBanner();
        }
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
}
