package com.jakeporter.classroster;

import com.jakeporter.classroster.service.ClassRosterServiceLayer;
import com.jakeporter.classroster.service.ClassRosterServiceLayerImpl;
import controller.ClassRosterController;
import dao.ClassRosterAuditDao;
import dao.ClassRosterAuditDaoFileImpl;
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
        // instantiate a data access object for CRUD operations
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // instantiate a data access object for writing to the audit log
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        // construct service layer object w/ DAOs
        ClassRosterServiceLayer myServiceLayer = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        // instantiate/construct the controller with the specified DAO and view
        ClassRosterController controller = new ClassRosterController(myServiceLayer, myView);
        
        controller.run();
    }
}
