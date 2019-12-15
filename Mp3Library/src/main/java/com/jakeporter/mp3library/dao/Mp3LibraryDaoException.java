package com.jakeporter.mp3library.dao;

/**
 *
 * @author jake
 */
public class Mp3LibraryDaoException extends Exception{

    public Mp3LibraryDaoException(String message){
        super(message);
    }
    
    public Mp3LibraryDaoException(String message, Throwable e){
        super(message, e);
    }
}
