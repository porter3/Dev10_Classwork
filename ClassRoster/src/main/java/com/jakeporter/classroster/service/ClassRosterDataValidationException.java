package com.jakeporter.classroster.service;

/**
 *
 * @author jake
 */
public class ClassRosterDataValidationException extends Exception{

    public ClassRosterDataValidationException(String message){
        super(message);
    }
    // remember, Throwable is the superclass of all errors and exceptions
    public ClassRosterDataValidationException(String message, Throwable e){
        super(message, e);
    }
}
