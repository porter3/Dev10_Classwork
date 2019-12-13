package com.jakeporter.addressbook.controller;

import com.jakeporter.addressbook.dao.AddressBookDao;
import com.jakeporter.addressbook.dto.Person;
import com.jakeporter.addressbook.ui.AddressBookView;
import java.util.List;

/**
 *
 * @author jake
 */
public class AddressBookController {

    AddressBookView view;
    AddressBookDao dao;
    
    // gotta make sure them dependencies are injected
    public AddressBookController(AddressBookView view, AddressBookDao dao){
        this.view = view;
        this.dao = dao;
    }
    
    public void run(){
        
        boolean keepRunning = true;
        int menuSelection = 0;
        
        // add exception handling later
            while (keepRunning){
                
                // viewer prints menu and gets selection
                menuSelection = getMenuSelection();
                
                switch(menuSelection){
                    case 1:
                        createAddress();
                        break;
                    case 2:
                        deleteAddress();
                        break;
                    case 3:
                        viewAddress();
                        break;
                    case 4:
                        viewAddressCount();
                        break;
                    case 5:
                        viewAllAddresses();
                        break;
                    case 6:
                        keepRunning = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
    }
    
    private int getMenuSelection(){
        int menuSelection = view.printMenuAndGetSelection();
        return menuSelection;
    }
    
    //createAddress()
    private void createAddress(){
        // display function banner
        view.displayCreateAddressBanner();
        // get person to add
        Person currentPerson = view.getNewAddressInfo();
        // add person to data structure in memory (map)
        dao.addPerson(currentPerson.getLastName(), currentPerson);
        // display function success banner
        view.displayCreateSuccessBanner();
    }
    
    private void deleteAddress(){
        view.displayDeleteAddressBanner();
        // get person to remove
        String lastName = view.getLastNameForRemoval();
        // remove person from data structure in memory if they exist
        Person removedPerson = dao.removePerson(lastName);
        if (removedPerson == null){
            view.displayDeletionFailureBanner();
        }
        else{
            view.displayDeletionSuccessBanner();
        }
    }
    
    // view specific address
    private void viewAddress(){
        view.displayViewAddressBanner();
        // get last name choice for person to view
        String lastName = view.getLastNameForViewing();
        // load address data to view
        Person currentPerson = dao.findPerson(lastName);
        // view address
        view.displayAddress(currentPerson);
    }
    
    private void viewAddressCount(){
        view.displayViewAddressCountBanner();
        int addressCount = dao.getPersonCount();
        view.viewAddressCount(addressCount);
    }
    
    // view all addresses
    private void viewAllAddresses(){
        view.displayViewAllBanner();
        List<Person> listOfAllPersons = dao.getAllPersons();
        // pass in listOfAllPersons to view method
        view.displayAllAddresses(listOfAllPersons);
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
}
