package com.jakeporter.flooringmastery;

import com.jakeporter.flooringmastery.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jake
 */
public class App {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringController controller = ctx.getBean("controller", FlooringController.class);
    
}
