package com.jakeporter.flooringmastery.controller;

import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import com.jakeporter.flooringmastery.service.FlooringServiceLayer;
import com.jakeporter.flooringmastery.ui.FlooringView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jake
 */
public class FlooringController {

    private FlooringView view;
    private FlooringServiceLayer service;
    
    FlooringController(FlooringView view, FlooringServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void run(){
        while(true){
            // prompt user for mode
            // set config file to mode
            // load order data

            int menuChoice = printMenuAndGetInput();
            
            try{
                switch(menuChoice){
                    case 1:
                        // display all orders or display by date (provide options)
                        displayOrders();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        // edit order, date can be modifiable
                        // editOrder();
                        break;
                    case 4:
                        // delete order
                        break;
                    case 5:
                        // save current work
                        break;
                    // STRETCH GOALS: print an invoice showing calculations
                            // use regex for date submission flexibility
                    case 6:
                        displayExitMessage();
                        return;
                    default:
                        displayUnknownCommand();
                }
            }
            catch(TaxPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
        }
    }
    
    private int printMenuAndGetInput(){
        return view.printMenuAndGetInput();
    }
    
    private void displayOrders(){
        int choice = view.printDisplayMenuAndGetInput();
        List<Order> allOrders = service.getAllOrders();
        if (choice == 1){
            view.displayAllOrders(allOrders);
        }
        else{
            LocalDate date = view.promptForDate();
            view.displayOrdersOfDate(date, allOrders);
        }
    }
    
    private void addNewOrder() throws TaxPersistenceException{
        service.loadProductsAndTaxRates();
        // get available materials
        List<Product> productList = service.getProductsAsList();
        // get order data, pass in available materials for selection
        Order newOrder = view.getNewOrder(productList);
        // populate Order object
        newOrder = service.populateOrderFields(newOrder);
        // display order details
        view.displayOrder(newOrder);
        // prompt user if they want to commit order
        boolean commitOrder = view.promptToCommitOrder();
        // commit order if they want to
        if (commitOrder == true){
            service.addOrder(newOrder);
        }
    }
    
    private void editOrder(){
        // display editing banner
            // view.displayEditBanner();
        // prompt user for date
            // LocalDate orderDate = view.getOrderDate();
        // check if date exists
            // boolean dateExists = service.checkIfDateHasOrders(orderDate);
        // prompt user for order number
            // String orderNumber = view.getOrderNumber();
        // check if order for date exists
            // boolean orderForDateExists = service.checkOrderOnDate(orderDate, orderNumber);
        // create new order and get editing information
            // Order editedOrder = view.getEditedOrder(orderNumber);
            // 
        // put that order object back in the DAO
            // service.addOrder(editedOrder);
        // IF IT DOESN' EXIST, create exception and go back to main menu
            
    }
    
    private void displayExitMessage(){
        view.displayExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
}
