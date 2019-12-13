package com.jakeporter.addressbook.dao;

/**
 *
 * @author jake
 */
public class AddressBookDaoException extends Exception{

    public AddressBookDaoException(String message){
        super(message);
    }
    
    // Throwable is the base class, can therefore handle exceptions and errors
    public AddressBookDaoException(String message, Throwable e){
        super(message, e);
    }
}
