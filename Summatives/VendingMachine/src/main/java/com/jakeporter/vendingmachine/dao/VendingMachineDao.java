package com.jakeporter.vendingmachine.dao;

import com.jakeporter.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author jake
 */
public interface VendingMachineDao {

    public List<Item> getInventory() throws InventoryPersistenceException;
    
    public void updateInventory(Item item) throws InventoryPersistenceException;
    
    public Item getItem(String itemName) throws InventoryPersistenceException;
    
    public String marshallItem(Item itemToConvert);
    
    public Item unmarshallItem(String itemString);
    
    public void writeInventory() throws InventoryPersistenceException;
    
    public void loadInventory() throws InventoryPersistenceException;
    
    public void loadNewItemsIntoInventory() throws InventoryPersistenceException;
    
    /**
     * Method for testing only
     * @throws com.jakeporter.vendingmachine.dao.InventoryPersistenceException
     */
    public void clearInventory() throws InventoryPersistenceException;
    
    /**
     * Method for testing only
     */
    public void addToInventory(Item item);
    
}
