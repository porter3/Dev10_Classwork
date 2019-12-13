package com.jakeporter.addressbook.dao;

import com.jakeporter.addressbook.dto.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jake
 */
public class AddressBookDaoImpl implements AddressBookDao {
    
    HashMap<String, Person> people = new HashMap();
    public static final String ADDRESS_BOOK_FILE = "addressBook.txt";
    public static final String DELIMITER = "::";
    
    public Person addPerson(String lastName, Person person){
        // load person into map, //TODO: call loadAddressBook() later to load from disk
        people.put(lastName, person);
        return person;
    }
    
    public Person removePerson(String lastName){
        return people.remove(lastName);
    }
    
    public Person findPerson(String lastName){
        return people.get(lastName);
    }
    
    public int getPersonCount(){
        return people.size();
    }
    
    public List<Person> getAllPersons(){
        // ArrayList constructor can take collections as parameters
        return new ArrayList<Person>(people.values());
    }
    
    public String marshallPerson(Person aPerson){
        // format: <field>::<field>::<field>
        return aPerson.getFirstName() + DELIMITER + aPerson.getLastName() + DELIMITER + 
                aPerson.getAddress().toString("street") + DELIMITER + 
                aPerson.getAddress().toString("city") + DELIMITER + 
                aPerson.getAddress().toString("state") + DELIMITER + 
                aPerson.getAddress().toString("zip");
    }
    
//    public Person unmarshallPerson(){
//        
//    }
    
    public void writeAddressBook() throws AddressBookDaoException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(ADDRESS_BOOK_FILE));
        }
        catch (IOException e){
            throw new AddressBookDaoException("Could not save address data", e);
        }
    }
    
//    public void loadAddressBook(){
//        
//    }
}
