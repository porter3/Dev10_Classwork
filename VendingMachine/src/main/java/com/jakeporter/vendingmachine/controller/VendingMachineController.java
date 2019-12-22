package com.jakeporter.vendingmachine.controller;

import com.jakeporter.vendingmachine.dao.InventoryPersistenceException;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import com.jakeporter.vendingmachine.service.VendingMachineServiceLayer;
import com.jakeporter.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        while(programRuns){
            try{
                displayMenu();
                BigDecimal userMoney = getMoney();
                BigDecimal remainder = vend(userMoney);
                vendAgainWithChange = promptToSelectAgain();
                while (vendAgainWithChange){
                    displayMenu();
                    remainder = vend(remainder);
                    vendAgainWithChange = promptToSelectAgain();
                }
                programRuns = promptToExit();
            }
            catch(InventoryPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
            
        }
    }
    
    
    public void displayMenu() throws InventoryPersistenceException{
        List<Item> inventory = service.getInventory();
        view.displayMenu(inventory);
    }
    
    public BigDecimal getMoney(){
        return view.getMoney();
    }
  
    public BigDecimal vend(BigDecimal userMoney) throws InventoryPersistenceException{
        String selection = view.getSelection();
        Item selectedItem = service.getItem(selection);
        service.vendItem(selectedItem); //-- DAO loads inventory, decrements inventory count in data structure, writes to inventory
        // calculate change to return to user
        Change changeReturned = service.calculateUserChange(userMoney, selectedItem);
        view.displaySuccessAndChangeReturned(changeReturned); //-- displays information that service.vendItem() returns plus the total of the change
        return changeReturned.getTotalChangeInDollars();
    }
    
    public boolean promptToSelectAgain(){
        return view.promptToSelectAgain();
    }
    
    public boolean promptToExit(){
        return view.promptToExit();
    }
    
}
