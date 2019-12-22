package com.jakeporter.vendingmachine.service;

import com.jakeporter.vendingmachine.dao.InventoryPersistenceException;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jake
 */
public interface VendingMachineServiceLayer {

    public List<Item> getInventory() throws InventoryPersistenceException;
    
    public Item getItem(String itemName) throws InventoryPersistenceException;
    
    public void vendItem(Item item) throws InventoryPersistenceException;
    
    public Change calculateUserChange(BigDecimal userMoney, Item itemVended);
}
