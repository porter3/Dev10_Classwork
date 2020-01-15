package com.sg.jdbctcomplexexample;

import com.sg.jdbctcomplexexample.controller.MeetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author kylerudy
 */
@SpringBootApplication /* This annotation causes Spring to start identifying components
                            to initialize the base configuration of everything.
                            Wraps several annotations into one.*/

/* CommandLineRunner allows us to run application through command line, but will
call the run() method and start the program when tests are run */

public class App implements CommandLineRunner {
    
    @Autowired
    MeetingController controller;

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        controller.run();
    }
    
}
