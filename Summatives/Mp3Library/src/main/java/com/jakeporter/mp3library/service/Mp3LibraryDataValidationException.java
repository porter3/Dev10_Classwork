package com.jakeporter.mp3library.service;

/**
 *
 * @author jake
 */
public class Mp3LibraryDataValidationException extends Exception{

    public Mp3LibraryDataValidationException(String message){
        super(message);
    }
    
    public Mp3LibraryDataValidationException(String message, Throwable e){
        super(message, e);
    }
}
