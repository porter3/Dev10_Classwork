package com.jakeporter.classroster;

import controller.ClassRosterController;
import dao.ClassRosterDao;
import dao.ClassRosterDaoFileImpl;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        // instantiate an IO object
        UserIO myIo = new UserIOConsoleImpl();
        // instantiante a viewer object (will display UI, take/store user input for the controller to use)
        ClassRosterView myView = new ClassRosterView(myIo);
        // instantiate a data access object (will perform logic for the controller)
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // instantiate/construct the controller with the specified DAO and view
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        
        controller.run();
    }
}
