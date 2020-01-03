package com.jakeporter.flooringmastery.dao;

/**
 *
 * @author jake
 */
public class ConfigurationPersistenceException extends Exception{

    ConfigurationPersistenceException(String message){
        super(message);
    }
    
    ConfigurationPersistenceException(String message, Throwable e){
        super(message, e);
    }
}
