package com.jakeporter.flooringmastery.controller;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.service.FlooringServiceLayer;
import com.jakeporter.flooringmastery.ui.FlooringView;

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
            switch(menuChoice){
                case 1:
                    // display orders by date
                    break;
                case 2:
                    // add order
                    break;
                case 3:
                    // edit order
                    break;
                case 4:
                    // delete order
                    break;
                case 5:
                    // save current work
                    break;
                case 6:
                    displayExitMessage();
                    return;
                default:
                    displayUnknownCommand();
            }
        }
    }
    
    private int printMenuAndGetInput(){
        return view.printMenuAndGetInput();
    }
    
    private void addNewOrder(){
        // get order data
        Order newOrder = view.getNewOrder();
        // generate unique order number
        // newOrder.setOrderNumber(service.generateOrderNumber());
        // create order object and assign fields
        // display order details
        // prompt user if they want to commit order
            // commit order if they want to
    }
    
    private void displayExitMessage(){
        view.displayExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
}
