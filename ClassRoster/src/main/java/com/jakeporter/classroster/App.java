package com.jakeporter.classroster;

import com.jakeporter.classroster.service.ClassRosterServiceLayer;
import com.jakeporter.classroster.service.ClassRosterServiceLayerImpl;
import com.jakeporter.classroster.controller.ClassRosterController;
import com.jakeporter.classroster.dao.ClassRosterAuditDao;
import com.jakeporter.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.jakeporter.classroster.dao.ClassRosterDao;
import com.jakeporter.classroster.dao.ClassRosterDaoFileImpl;
import com.jakeporter.classroster.ui.ClassRosterView;
import com.jakeporter.classroster.ui.UserIO;
import com.jakeporter.classroster.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
//        
//        // instantiate an IO object
//        UserIO myIo = new UserIOConsoleImpl();
//        // instantiante a viewer object (will display UI, take/store user input for the controller to use)
//        ClassRosterView myView = new ClassRosterView(myIo);
//        // instantiate a data access object for CRUD operations
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        // instantiate a data access object for writing to the audit log
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        // construct service layer object w/ DAOs
//        ClassRosterServiceLayer myServiceLayer = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//        // instantiate/construct the controller with the specified DAO and view
//        ClassRosterController controller = new ClassRosterController(myServiceLayer, myView);
//        
//        controller.run();
            
        // instantiate the Spring container, passing in the name of the configuration file
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
