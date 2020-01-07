package com.jakeporter.vendingmachine.dao;

import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jake
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    Item onlyItem = new Item();
    List<Item> listOfItems = new ArrayList();
    
    public VendingMachineDaoStubImpl(){
        onlyItem.setName("Cookies");
        onlyItem.setCost(new BigDecimal("200"));
        onlyItem.setInventoryCount(3);
        listOfItems.add(onlyItem);
    }

    @Override
    public List<Item> getInventory() throws InventoryPersistenceException {
        return listOfItems;
    }

    @Override
    public void updateInventory(Item item) throws InventoryPersistenceException {
        listOfItems.get(0).setInventoryCount(listOfItems.get(0).getInventoryCount()-1);
    }

    @Override
    public Item getItem(String itemName) throws InventoryPersistenceException {
        if (itemName.equals(onlyItem.getName())){
            return onlyItem;
        }
        else{
            return null;
        }
    }

    @Override
    public String marshallItem(Item itemToConvert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item unmarshallItem(String itemString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeInventory() throws InventoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInventory() throws InventoryPersistenceException { 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearInventory() throws InventoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToInventory(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadNewItemsIntoInventory() throws InventoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
