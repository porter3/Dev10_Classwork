package controller;

import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author jake
 */

// This orchestrates the application. Will be composed of the view and data access objects
public class ClassRosterController {

    ClassRosterView view = new ClassRosterView();
    // creating an instance, but with a reference to its interface (so we can only use the methods defined in the interface?)
    private UserIO io = new UserIOConsoleImpl();
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
            
            menuSelection = getMenuSelection();
        
        switch(menuSelection){
            case 1:
                io.print("LIST STUDENTS");
                //TODO
                break;
            case 2:
                io.print("CREATE NEW STUDENT");
                //TODO
                break;
            case 3:
                io.print("VIEW STUDENTS");
                //TODO
                break;
            case 4:
                io.print("REMOVE STUDENTS");
                //TODO
                break;
            case 5:
                keepGoing = false;
                break;
            default:
                io.print("UNKNOWN COMMAND");
        }
        }
    io.print("GOODBYE");
    
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
}
