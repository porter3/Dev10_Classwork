/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.addressbook.dao;

import com.jakeporter.addressbook.dto.Person;
import java.util.List;

/**
 *
 * @author jake
 */
public interface AddressBookDao{
    
    /**
     * Adds person to address book
     * 
     * @param lastName with which person is associated
     * @param person to be added
     * @return person previously associated with lastName, null otherwise
     */
    Person addPerson(String lastName, Person person) throws AddressBookDaoException;
    
    
    /**
     * Removes person associated with last name
     * 
     * @param personId
     * @return Person object that was removed, null if nonexistent
     */
    Person removePerson(String lastName) throws AddressBookDaoException;
    
    /**
     * Returns the Person object associated with the given id
     * 
     * @param lastName
     * @return Person object associated with lastName, null if nonexistent
     */
    Person findPerson(String lastName) throws AddressBookDaoException;
    
    /**
     * Returns number of addresses in address book
     * 
     * @return number of Person objects in address book
     */
    int getPersonCount() throws AddressBookDaoException;
    
    /**
     * 
     * @return List holding all Person objects in address book
     */
    List<Person> getAllPersons() throws AddressBookDaoException;
    
    /**
     * 
     * convert Person objects to String values for writing to file
     * 
     * @param Person object to be marshalled into String
     * @return String value of Person fields separated by delimiter
     */
    String marshallPerson(Person aPerson);
    
    /**
     * 
     * convert String value to Person object for loading into map
     * 
     * @param Delimited string to unmarshall into a Person object
     * @return Person
     */
    Person unmarshallPerson(String personAsText);
    
    /**
     * 
     * Writes String of person data to file
     */
    void writeAddressBook() throws AddressBookDaoException;
    
    /**
     * 
     * Reads Person data from address book into map
     */
    void loadAddressBook() throws AddressBookDaoException;
}
