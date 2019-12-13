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
    Person addPerson(String lastName, Person person);
    
    
    /**
     * Removes person associated with last name
     * 
     * @param personId
     * @return Person object that was removed, null if nonexistent
     */
    Person removePerson(String lastName);
    
    /**
     * Returns the Person object associated with the given id
     * 
     * @param lastName
     * @return Person object associated with lastName, null if nonexistent
     */
    Person findPerson(String lastName);
    
    /**
     * Returns number of addresses in address book
     * 
     * @return number of Person objects in address book
     */
    int getPersonCount();
    
    /**
     * 
     * @return List holding all Person objects in address book
     */
    List<Person> getAllPersons();
    
    /**
     * 
     * convert Person objects to String values for writing to file
     * 
     * @return String value of Person fields separated by delimiter
     */
    String marshallPerson();
    
//    /**
//     * 
//     * convert String value to Person object for loading into map
//     * 
//     * @return Person
//     */
//    Person unmarshallPerson();
    
    /**
     * 
     * Writes String of person data to file
     */
    void writeAddressBook() throws AddressBookDaoException;
    
//    /**
//     * 
//     * Reads Person data from address book into map
//     */
//    void loadAddressBook();
}
