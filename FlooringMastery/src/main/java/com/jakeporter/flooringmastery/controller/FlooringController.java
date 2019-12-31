package com.jakeporter.flooringmastery.controller;

import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import com.jakeporter.flooringmastery.service.FlooringServiceLayer;
import com.jakeporter.flooringmastery.ui.FlooringView;
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
                        break;
                    case 4:
                        // delete order
                        break;
                    case 5:
                        // save current work
                        break;
                    // STRETCH GOAL: print an invoice showing calculations
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
        //view.printDisplayMenuAndGetInput();
        List<Order> allOrders = service.getAllOrders();
        //IF VIEWING ALL ORDERS
            view.displayAllOrders(allOrders);
        //IF VIEWING BY DATE
            //view.displayOrdersOfDate
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
    
    private void displayExitMessage(){
        view.displayExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
}
