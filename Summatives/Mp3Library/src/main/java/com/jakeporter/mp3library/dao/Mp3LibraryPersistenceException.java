package com.jakeporter.mp3library.dao;

/**
 *
 * @author jake
 */
public class Mp3LibraryPersistenceException extends Exception{

    public Mp3LibraryPersistenceException(String message){
        super(message);
    }
    
    public Mp3LibraryPersistenceException(String message, Throwable e){
        super(message, e);
    }
}
