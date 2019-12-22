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
        while(programRuns){
            
            try{
                hasInventory = displayMenu();
                if (hasInventory == false){
                    return;
                }
            }
            catch(InventoryPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
                BigDecimal userMoney = getMoney();
                BigDecimal remainder;
                try{
                    remainder = vend(userMoney);
                }
                catch(InventoryPersistenceException | NoItemInventoryException | InsufficientFundsException e){
                    view.displayErrorMessage(e.getMessage());
                    continue;
                }
                vendAgainWithChange = promptToSelectAgain();
                while (vendAgainWithChange){
                    try{
                        hasInventory = displayMenu();
                        if (hasInventory == false){
                            return;
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
                    vendAgainWithChange = promptToSelectAgain();
                }
                programRuns = promptToExit();
            
        }
    }
    
    
    public boolean displayMenu() throws InventoryPersistenceException{
        List<Item> inventory = service.getInventory();
        return view.displayMenu(inventory);
    }
    
    public BigDecimal getMoney(){
        return view.getMoney();
    }
  
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
    
    public boolean promptToSelectAgain(){
        return view.promptToSelectAgain();
    }
    
    public boolean promptToExit(){
        return view.promptToExit();
    }
    
}
