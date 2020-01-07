package com.jakeporter.vendingmachine.service;

/**
 *
 * @author jake
 */
public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message){
        super("Item is out of stock");
    }
    
    public NoItemInventoryException(String message, Throwable e){
        super("Item is out of stock", e);
    }
}
