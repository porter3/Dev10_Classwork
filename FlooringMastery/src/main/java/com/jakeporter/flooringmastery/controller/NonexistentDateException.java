package com.jakeporter.flooringmastery.controller;

/**
 *
 * @author jake
 */
public class NonexistentDateException extends Exception {

    public NonexistentDateException(String message) {
        super(message);
    }
    
    public NonexistentDateException(String message, Throwable e){
        super(message, e);
    }

}
