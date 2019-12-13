package com.jakeporter.addressbook.ui;

import com.jakeporter.addressbook.dto.Address;
import com.jakeporter.addressbook.dto.Person;
import java.util.List;

/**
 *
 * @author jake
 */
public class AddressBookView {

    UserIO io;
    
    public AddressBookView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        // print menu for user
        io.print("Main Menu");
        io.print("1. Create New Address");
        io.print("2. Delete Address");
        io.print("3. View an Address");
        io.print("4. View Current Address Count");
        io.print("5. View List of Addresses");
        io.print("6. Exit Program");
        
        return io.readInt("Please select one of the options above: ", 1, 6);
    }
    
    public Person getNewAddressInfo(){
        String firstName = io.readString("Please enter Person's first name");
        String lastName = io.readString("Please enter person's last name");
        String street = io.readString("Please enter street number and name");
        String city = io.readString("Please enter city");
        String state = io.readString("Please enter state");
        String zip = io.readString("Please enter zip code");
        Address currentAddress = new Address();
        currentAddress.setStreet(street);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);
        Person currentPerson = new Person();
        currentPerson.setFirstName(firstName);
        currentPerson.setLastName(lastName);
        currentPerson.setAddress(currentAddress);
        return currentPerson;
    }
    
    public String getLastNameForRemoval(){
        return io.readString("Please enter the person's last name for the address you want to remove");
    }
    
    public String getLastNameForViewing(){
        return io.readString("Please enter the last name of the address you would like to view.");
    }
    
    public void displayAddress(Person person){
        if (person != null){
            io.print(person.getFirstName() + " " + person.getLastName() + "\n"
                    // boolean parameter invokes a toString() without the "Address: " tag
                     + person.getAddress().toString(true));
        }
        else{
            io.print("Address does not exist");
        }
        io.readString("Hit enter to continue\n");
    }
    
    public void viewAddressCount(int numberOfAddresses){
        if (numberOfAddresses > 1){
            io.print("There are " + numberOfAddresses + " addresses in your address book.");
        }
        else if (numberOfAddresses == 1){
            io.print("There is 1 address in your address book.");
        }
        else{
            io.print("There are no addresses in your address book.");
        }
    }
    
    public void displayAllAddresses(List<Person> listOfAllPersons){
        for (Person currentPerson : listOfAllPersons){
            io.print(currentPerson.getFirstName() + " " + currentPerson.getLastName()
                     + currentPerson.getAddress());
        }
        // readString returns Scanner.nextLine()
        io.readString("Hit enter to continue\n");
    }
    
    public void displayCreateAddressBanner(){
        io.print("ADD NEW ADDRESS");
    }
    
    public void displayCreateSuccessBanner(){
        io.print("Address successfully created.\n");
    }
    
    public void displayDeleteAddressBanner(){
        io.print("DELETE ADDRESS");
    }
    
    public void displayDeletionSuccessBanner(){
        io.print("Deletion successful\n");
    }
    
    public void displayDeletionFailureBanner(){
        io.print("Deletion unsuccessful, person does not exist\n");
    }
    
    public void displayViewAddressCountBanner(){
        io.print("VIEW ADDRESS COUNT");
    }
    
    public void displayViewAllBanner(){
        io.print("VIEW ALL ADDRESSES");
    }
    
    public void displayViewAddressBanner(){
        io.print("VIEW ADDRESS");
    }
    
    public void displayExitBanner(){
        io.print("Exiting program");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Command not recognized\n");
    }
}
