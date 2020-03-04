package com.jakeporter.springaopexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jake
 */
public class App {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService customerService = ctx.getBean("customerService", CustomerService.class);
        
        System.out.println("--------------------");
        customerService.printName();
        System.out.println("--------------------");
        customerService.printURL();
        System.out.println("--------------------");
        
        try{
            customerService.printThrowException();
        }
        catch(Exception e){
            
        }
    }
}
