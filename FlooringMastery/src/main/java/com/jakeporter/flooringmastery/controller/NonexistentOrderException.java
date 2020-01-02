package com.jakeporter.flooringmastery.controller;

/**
 *
 * @author jake
 */
public class NonexistentOrderException extends Exception {

    public NonexistentOrderException(String message) {
        super(message);
    }
    
    public NonexistentOrderException(String message, Throwable e){
        super(message, e);
    }

}
