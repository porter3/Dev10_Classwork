package com.jakeporter.flooringmastery.dao;

/**
 *
 * @author jake
 */
public class UnknownConfigurationException extends Exception {

    public UnknownConfigurationException(String message) {
        super(message);
    }
    
    public UnknownConfigurationException(String message, Throwable e){
        super(message, e);
    }

}
