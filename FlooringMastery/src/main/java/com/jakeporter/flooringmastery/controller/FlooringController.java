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
                        editOrder();
                        break;
                    case 4:
                        // deleteOrder();
                        break;
                    case 5:
                        // save current work
                        break;
                    // STRETCH GOALS: print an invoice showing calculations
                            // use regex for date submission flexibility
                            // add leading zeroes to order numbers
                    case 6:
                        displayExitMessage();
                        return;
                    default:
                        displayUnknownCommand();
                }
            }
            catch(TaxPersistenceException | NonexistentOrderException | NonexistentDateException e){
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
            LocalDate date = view.getDateForDisplaying();
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
    
    private void editOrder() throws TaxPersistenceException, NonexistentOrderException, NonexistentDateException{
        // display editing banner
            view.displayEditBanner();
        // prompt user for date
            LocalDate orderDate = view.getDateForEditing();
        // get orders from date
            List<Order> ordersFromDate = service.getOrdersFromDate(orderDate);
            if (ordersFromDate.isEmpty()){
                throw new NonexistentDateException("The specified date does not have any orders associated with it");
            }
        // prompt user for order number
            String orderNumber = view.getOrderNumber(orderDate);
        // check if order for date exists
            Order orderToEdit = service.checkOrderOnDate(ordersFromDate, orderNumber);
            if (orderToEdit == null){
                throw new NonexistentOrderException("The specified order for that date does not exist");
            }
        // load product list and tax rates
            service.loadProductsAndTaxRates();
            List<Product> productList = service.getProductsAsList();
        // create new order and get editing information
            Order editedOrder = view.getEditedOrder(orderToEdit, productList);
        // populate new order
            editedOrder = service.populateOrderFields(editedOrder);
        // put that order object back in the DAO
            service.addOrder(editedOrder);            
    }
    
    private void displayExitMessage(){
        view.displayExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
}
