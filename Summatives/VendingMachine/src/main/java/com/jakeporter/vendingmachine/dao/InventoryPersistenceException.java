package com.jakeporter.vendingmachine.dao;

/**
 *
 * @author jake
 */
public class InventoryPersistenceException extends Exception{

    public InventoryPersistenceException(String message){
        super(message);
    }
    
    public InventoryPersistenceException(String message, Throwable e){
        super(message, e);
    }
}
