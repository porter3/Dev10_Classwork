package com.jakeporter.vendingmachine.controller;

import com.jakeporter.vendingmachine.service.NoItemInventoryException;
import com.jakeporter.vendingmachine.dao.InventoryPersistenceException;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import com.jakeporter.vendingmachine.service.InsufficientFundsException;
import com.jakeporter.vendingmachine.service.VendingMachineServiceLayer;
import com.jakeporter.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jake
 */
public class VendingMachineController {
    
    VendingMachineServiceLayer service;
    VendingMachineView view;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.service = service;
        this.view = view;
    }

    public void run(){
        
        boolean programRuns = true;
        boolean vendAgainWithChange = true;
        boolean hasInventory = true;
        BigDecimal remainder = new BigDecimal("0");
        BigDecimal userMoney;
        while(programRuns){
            
            try{
                // displayMenu returns a boolean that is false if all items in inventory have an inventoryCount of 0
                hasInventory = displayMenu();
                // load items with the same name (but different inventoryCounts) into DAO, display menu again
                if (hasInventory == false){
                    loadNewInventory();
                    displayMenu();
                }
            }
            catch(InventoryPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
                // prompt user/get money input
                userMoney = getMoney(remainder);
                try{
                    // assign the total of the leftover change to remainder
                    remainder = vend(userMoney);
                }
                catch(InventoryPersistenceException | NoItemInventoryException | InsufficientFundsException e){
                    view.displayErrorMessage(e.getMessage());
                    continue;
                }
                // prompt user to repeat process without putting in more money
                vendAgainWithChange = promptToSelectAgain();
                while (vendAgainWithChange){
                    try{
                        // check if inventory is empty again, restock if so
                        hasInventory = displayMenu();
                        if (hasInventory == false){
                            loadNewInventory();
                            displayMenu();
                        }
                    }
                    catch(InventoryPersistenceException e){
                        view.displayErrorMessage(e.getMessage());
                    }
                    try{
                        remainder = vend(remainder);
                    }
                    catch(InventoryPersistenceException | NoItemInventoryException | InsufficientFundsException e){
                        view.displayErrorMessage(e.getMessage());
                    }
                    // prompt to vend again with leftover change
                    vendAgainWithChange = promptToSelectAgain();
                }
                programRuns = promptToExit();
            
        }
    }
    
    // display menu and return false if all items in inventoryCount are 0
    public boolean displayMenu() throws InventoryPersistenceException{
        List<Item> inventory = service.getInventory();
        return view.displayMenu(inventory);
    }
    
    // prompt user for money and return it as a BigDecimal value of 00.00 (not a whole number)
    public BigDecimal getMoney(BigDecimal userMoney){
        BigDecimal newMoney = view.getMoney();
        return newMoney.add(userMoney);
    }
  
    /* gets selection from user, validates that item has inventoryCount of >0, validates user has enough money to purchase it,
        updates persisted inventory after taking user's selection, displays success and how much change returned, returns total change in BigDecimal form
    */
    public BigDecimal vend(BigDecimal userMoney)
            throws InventoryPersistenceException, InsufficientFundsException, NoItemInventoryException{
        String selection = view.getSelection();
        Item selectedItem = service.getItem(selection);
        service.validateInventory(selectedItem);
        service.validateFunds(userMoney, selectedItem);
        // DAO loads inventory, decrements inventory count in data structure, writes to inventory
        service.vendItem(selectedItem);
        // calculate change to return to user
        Change changeReturned = service.calculateUserChange(userMoney, selectedItem);
        // displays information that service.vendItem() returns plus the total of the change
        view.displaySuccessAndChangeReturned(changeReturned);
        return changeReturned.getTotalChangeInDollars();
    }
    
    // used for loading new items into inventory when all items have inventoryCount of 0
    private void loadNewInventory() throws InventoryPersistenceException{
        service.loadNewInventory();
    }
    
    public boolean promptToSelectAgain(){
        return view.promptToSelectAgain();
    }
    
    public boolean promptToExit(){
        return view.promptToExit();
    }
    
}
