package com.jakeporter.simplestspringmvcbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author jake
 */

@SpringBootApplication
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args); // why is args being passed in here?
    }
}
