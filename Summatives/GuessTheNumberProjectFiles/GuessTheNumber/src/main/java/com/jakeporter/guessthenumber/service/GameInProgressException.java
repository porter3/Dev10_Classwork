package com.jakeporter.guessthenumber.service;

/**
 *
 * @author jake
 */
public class GameInProgressException extends Exception {

    public GameInProgressException(String message) {
        super(message);
    }
    
    public GameInProgressException(String message, Throwable e){
        super(message, e);
    }

}
