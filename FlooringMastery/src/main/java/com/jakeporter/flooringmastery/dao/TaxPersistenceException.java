package com.jakeporter.flooringmastery.dao;

/**
 *
 * @author jake
 */
public class TaxPersistenceException extends Exception {

    public TaxPersistenceException(String message) {
        super(message);
    }

    public TaxPersistenceException(String message, Throwable e){
        super(message, e);
    }
}
