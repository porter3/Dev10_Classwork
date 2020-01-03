package com.jakeporter.flooringmastery.controller;

import com.jakeporter.flooringmastery.dao.ConfigurationPersistenceException;
import com.jakeporter.flooringmastery.dao.OrderPersistenceException;
import com.jakeporter.flooringmastery.dao.TaxPersistenceException;
import com.jakeporter.flooringmastery.dao.UnknownConfigurationException;
import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import com.jakeporter.flooringmastery.service.FlooringServiceLayer;
import com.jakeporter.flooringmastery.ui.FlooringView;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
        // prompt user for mode
        int configValue = promptMode();
        // set config file to mode
        try{
            setMode(configValue);
        }
        catch(ConfigurationPersistenceException e){
            displayErrorMessage("Could not write to config file");
            return;
        }

        while(true){
            int menuChoice = printMenuAndGetInput();
            try{
                loadOrders();
                switch(menuChoice){
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        deleteOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    // STRETCH GOALS:
                            // use regex for date submission flexibility, reading config file
                            // add leading zeroes to order numbers
                    case 6:
                        displayExitMessage();
                        return;
                    default:
                        displayUnknownCommand();
                }
            }
            catch(TaxPersistenceException | ConfigurationPersistenceException 
                    | UnknownConfigurationException | OrderPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
        }
    }
    
    private int promptMode(){
        return view.promptMode();
    }
    
    private void setMode(int configValue) throws ConfigurationPersistenceException{
        service.setConfig(configValue);
    }
    
    private void loadOrders() throws OrderPersistenceException{
        service.loadOrders();
    }
    
    private int printMenuAndGetInput(){
        return view.printMenuAndGetInput();
    }
    
    private void displayOrders(){
        // check if there are orders to display
        if(service.getAllOrders().isEmpty()){
            view.displayErrorMessage("There are no orders to display.");
            return;
        }
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
        // get set of state abbreviations for validation
        Set<String> states = service.getStateList();
        // get order data, pass in available materials for selection
        Order newOrder = view.getNewOrder(productList, states);
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
    
    private void editOrder() throws TaxPersistenceException{
        // display editing banner
        view.displayEditBanner();
        // check if any orders exist
        if (service.getAllOrders().isEmpty()){
            view.displayErrorMessage("There are no orders to edit.");
            return;
        }
        // prompt user for date
        LocalDate orderDate = view.getDateForEditing();
        // get orders from date
        List<Order> ordersFromDate = service.getOrdersFromDate(orderDate);
        if (ordersFromDate.isEmpty()){
            view.displayErrorMessage("The specified date does not have any orders associated with it.\n");
            return;
        }
        // prompt user for order number, returns it formatted with leading zeroes
        String orderNumber = view.getOrderNumber(orderDate);
        // check if order for date exists
        Order orderToEdit = service.checkOrderOnDate(ordersFromDate, orderNumber);
        if (orderToEdit == null){
            view.displayErrorMessage("The specified order for that date does not exist.\n");
            return;
        }
        // load product list and tax rates
        service.loadProductsAndTaxRates();
        List<Product> productList = service.getProductsAsList();
        // get set of state abbreviations for validation
        Set<String> states = service.getStateList();
        // create new order and get editing information
        Order editedOrder = view.getEditedOrder(orderToEdit, productList, states);
        // populate new order
        editedOrder = service.populateOrderFields(editedOrder);
        // put that order object back in the DAO
        service.addOrder(editedOrder);            
    }
    
    private void deleteOrder(){
        // display order deletion banner
        view.displayDeleteBanner();
        // check if orders exist to delete
        if(service.getAllOrders().isEmpty()){
            view.displayErrorMessage("There are no orders to delete.");
            return;
        }
        Order orderToDelete;
        while(true){
        // prompt user for date
            LocalDate orderDate = view.getDateForDeletion();
        // get orders from date
            List<Order> ordersFromDate = service.getOrdersFromDate(orderDate);
            if (ordersFromDate.isEmpty()){
                view.displayErrorMessage("The specified date does not have any orders associated with it");
                continue;
            }
        // prompt user for order number
            String orderNumber = view.getOrderNumber(orderDate);
        // check if order for date exists
            orderToDelete = service.checkOrderOnDate(ordersFromDate, orderNumber);
            if (orderToDelete == null){
                view.displayErrorMessage("The specified order for that date does not exist");
                continue;
            }
            break;
        }
        // if user decides to not confirm deletion, return them to main menu
        if (!view.promptDeletionConfirmation(orderToDelete)){
            return;
        }
        // remove order from DAO
        service.deleteOrder(orderToDelete);
        view.displayDeletionSuccess(orderToDelete);
    }
    
    private void saveCurrentWork() throws ConfigurationPersistenceException, UnknownConfigurationException, OrderPersistenceException{
        boolean inTrainingMode = service.checkIfTrainingMode();
        if (!inTrainingMode){
            service.saveOrders();
            view.displaySaveSuccess();
        }
        else{
            view.displayFakeSaveSuccess();
        }
    }
        
    private void displayExitMessage(){
        view.displayExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
    
    private void displayErrorMessage(String message){
        view.displayErrorMessage(message);
    }
}
