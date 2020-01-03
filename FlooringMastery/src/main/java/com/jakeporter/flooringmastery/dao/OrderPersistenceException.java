package com.jakeporter.flooringmastery.dao;

/**
 *
 * @author jake
 */
public class OrderPersistenceException extends Exception {

    public OrderPersistenceException(String message) {
        super(message);
    }
    
     public OrderPersistenceException(String message, Throwable e){
         super(message, e);
     }

}
