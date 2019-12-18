package com.jakeporter.addressbook.dao;

import com.jakeporter.addressbook.dto.Address;
import com.jakeporter.addressbook.dto.Person;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class AddressBookDaoImpl implements AddressBookDao {
    
    private HashMap<String, Person> people = new HashMap();
    public static final String ADDRESS_BOOK_FILE = "addressBook.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public Person addPerson(String lastName, Person person) throws AddressBookDaoException{
        // loads disk data into memory
        loadAddressBook();
        // load person into map
        Person aPerson = people.put(lastName, person);
        // write map back to disk
        writeAddressBook();
        return aPerson;
    }
    
    @Override
    public Person removePerson(String lastName) throws AddressBookDaoException{
        loadAddressBook();
        Person aPerson =  people.remove(lastName);
        writeAddressBook();
        return aPerson;
    }
    
    @Override
    public Person findPerson(String lastName) throws AddressBookDaoException{
        return people.get(lastName);
    }
    
    @Override
    public int getPersonCount() throws AddressBookDaoException{
        return people.size();
    }
    
    @Override
    public List<Person> getAllPersons() throws AddressBookDaoException{
        loadAddressBook();
        // ArrayList constructor can take collections as parameters
        return new ArrayList<Person>(people.values());
    }
    
    @Override
    public String marshallPerson(Person aPerson){
        // format: <field>::<field>::<field>
        return aPerson.getFirstName() + DELIMITER + aPerson.getLastName() + DELIMITER + 
                aPerson.getAddress().toString("street") + DELIMITER + 
                aPerson.getAddress().toString("city") + DELIMITER + 
                aPerson.getAddress().toString("state") + DELIMITER + 
                aPerson.getAddress().toString("zip");
    }
    
    @Override
    public Person unmarshallPerson(String personAsText){
        // split field at the String's delimiter, add to String array
        String[] personTokens = personAsText.split(DELIMITER);
        // create a new Address and Person
        Address anAddress = new Address();
        Person aPerson = new Person();
        // set each field of new Address and Person
        anAddress.setStreet(personTokens[2]);
        anAddress.setCity(personTokens[3]);
        anAddress.setState(personTokens[4]);
        anAddress.setZip(personTokens[5]);
        aPerson.setFirstName(personTokens[0]);
        aPerson.setLastName(personTokens[1]);
        aPerson.setAddress(anAddress);
        
        return aPerson;
    }
    
    @Override
    public void writeAddressBook() throws AddressBookDaoException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(ADDRESS_BOOK_FILE));
        }
        catch (IOException e){
            throw new AddressBookDaoException("Could not save address data", e);
        }
        
        // write out object file to addressBook.txt
        String personAsText;
        // get all addresses in book, load them into list
        List<Person> personList = getAllPersons();
        // iterate over each Person, marshalling them into a String
        for (Person currentPerson : personList){
            personAsText = marshallPerson(currentPerson);
            // write String to file
            out.println(personAsText);
            // force PrintWriter to write remaining line to file
            out.flush();
        }
        // clean up
        out.close();
    }
    
    @Override
    public void loadAddressBook() throws AddressBookDaoException{
        Scanner sc;
        
        try{
            // create scanner for reading file
            sc = new Scanner(new BufferedReader(new FileReader(ADDRESS_BOOK_FILE)));
        }
        // if file does not exist
        catch (FileNotFoundException exception){
            throw new AddressBookDaoException("Could not load address book into memory", exception);
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentPerson holds the most recent Person unmarshalled
        Person currentPerson;
        // Go through the text file one line at a time, unmarshall String into a Perosn object
        while (sc.hasNextLine()){
            // get the next line in file
            currentLine = sc.nextLine();
            // unmarshall it into a Person object
            currentPerson = unmarshallPerson(currentLine);
            //using the lastName as the map key, put the currentPerson into the map
            people.put(currentPerson.getLastName(), currentPerson);
        }
        sc.close();
    }
}
