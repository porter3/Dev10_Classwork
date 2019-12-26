package com.jakeporter.vendingmachine.service;

import com.jakeporter.vendingmachine.dao.InventoryPersistenceException;
import com.jakeporter.vendingmachine.dao.VendingMachineAuditDao;
import com.jakeporter.vendingmachine.dao.VendingMachineDao;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author jake
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    VendingMachineDao crudDao;
    VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao crudDao, VendingMachineAuditDao auditDao){
        this.crudDao = crudDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getInventory() throws InventoryPersistenceException{
        return crudDao.getInventory();
    }
    
    public Item getItem(String itemName) throws InventoryPersistenceException{
        return crudDao.getItem(itemName);
    }

    @Override
    public void vendItem(Item item) throws InventoryPersistenceException{
        crudDao.loadInventory();
        // decrements the selected in the data structure by 1
        crudDao.updateInventory(item);
        crudDao.writeInventory();
        auditDao.writeAuditEntry(item.getName() + " vended, " + item.getInventoryCount() + " remaining");
    }

    // REFACTOR
    @Override
    // method takens an argument of the user's money with scale of 2, converts to whole number
    // i.e. 2.50 -> 250
    public Change calculateUserChange(BigDecimal userMoney, Item itemVended) {
        // convert user's money to a whole number (i.e. 5.75 -> 575). Change DTO stores money in whole numbers
        BigDecimal moneyWholeNumber = userMoney.multiply(new BigDecimal("100"));
        // determine change by subtracting cost of item
        BigDecimal userChange = moneyWholeNumber.subtract(itemVended.getCost());
        return new Change(userChange.toString());
    }

    @Override
    public void validateFunds(BigDecimal userMoney, Item selectedItem) throws InsufficientFundsException {
        if (userMoney.compareTo(selectedItem.getCost().divide(new BigDecimal("100"), RoundingMode.HALF_UP)) < 0){
            throw new InsufficientFundsException("You don't have enough money to purchase that");
        }
    }

    @Override
    public void validateInventory(Item selectedItem) throws NoItemInventoryException {
        try{
            if (selectedItem.getInventoryCount() < 1){
                throw new NoItemInventoryException("That item is out of stock");
            }
        }
        catch(NullPointerException e){
            throw new NoItemInventoryException("That item does not exist");
        }
    }

    @Override
    public void loadNewInventory() throws InventoryPersistenceException {
        // loads the same items into inventory with inventoryCounts of 3
        crudDao.loadNewItemsIntoInventory();
        auditDao.writeAuditEntry("Restocked inventory");
    }
}
