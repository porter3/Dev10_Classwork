package com.jakeporter.vendingmachine.dao;

import com.jakeporter.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{

    Map<String, Item> inventory = new HashMap();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public List<Item> getInventory() throws InventoryPersistenceException{
        loadInventory();
        return new ArrayList(inventory.values());
    }
    
    @Override
    public void updateInventory(Item item) throws InventoryPersistenceException{
        item.setInventoryCount(item.getInventoryCount() - 1);
        inventory.replace(item.getName(), item);
    }

    @Override
    public Item getItem(String itemName){
        Item item = inventory.get(itemName);
        return item;
    }

    @Override
    public String marshallItem(Item itemToConvert) {
        return itemToConvert.getName() + DELIMITER
                + itemToConvert.getCost() + DELIMITER
                + itemToConvert.getInventoryCount();
    }

    @Override
    public Item unmarshallItem(String itemString) {
        String [] itemInfoFields = itemString.split(DELIMITER);
        // create new item object
        Item newItem = new Item();
        newItem.setName(itemInfoFields[0]);
        newItem.setCost(new BigDecimal(itemInfoFields[1]));
        newItem.setInventoryCount(Integer.parseInt(itemInfoFields[2]));
        return newItem;
    }

    @Override
    public void writeInventory() throws InventoryPersistenceException{
        // create PrintWriter refernce to write to file
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        }
        catch(IOException e){
            throw new InventoryPersistenceException("Could not save inventory data", e);
        }
        
        String itemString;
        List<Item> inventoryItems = getInventory();
        for (Item unmarshalledItem : inventoryItems){
            // marshall each item
            itemString = marshallItem(unmarshalledItem);
            // write string to file
            out.println(itemString);
            // force PrintWriter to write remaining line
            out.flush();
        }
        out.close();
    }

    @Override
    public void loadInventory() throws InventoryPersistenceException{
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        }
        catch(FileNotFoundException e){
            throw new InventoryPersistenceException("Could not load items in inventory", e);
        }
        // holds most recently read line from file
        String currentLine;
        // reference to point to new Item upon instantiation
        Item loadedItem;
        while (sc.hasNextLine()){
            // get next line
            currentLine = sc.nextLine();
            // unmarshall it into Item object
            loadedItem = unmarshallItem(currentLine);
            // load item into inventory
            inventory.put(loadedItem.getName(), loadedItem);
        }
        sc.close();
    }

    
    // TEST-ONLY METHODS
    
    @Override
    public void clearInventory() throws InventoryPersistenceException{
        ArrayList<Item> items = new ArrayList(inventory.values());
        for (Item item : items){
            inventory.remove(item.getName());
        }
        writeInventory();
    }

    @Override
    public void addToInventory(Item item) {
        inventory.put(item.getName(), item);
    }

    @Override
    public void loadNewItemsIntoInventory() throws InventoryPersistenceException {
        Item popcorn = new Item();
        popcorn.setName("Popcorn");
        popcorn.setCost(new BigDecimal("225"));
        popcorn.setInventoryCount(3);
        inventory.put(popcorn.getName(), popcorn);
        
        Item cookies = new Item();
        cookies.setName("Cookies");
        cookies.setCost(new BigDecimal("200"));
        cookies.setInventoryCount(3);
        inventory.put(cookies.getName(), cookies);
        
        Item water = new Item();
        water.setName("Water");
        water.setCost(new BigDecimal("150"));
        water.setInventoryCount(3);
        inventory.put(water.getName(), water);
        
        Item proteinBar = new Item();
        proteinBar.setName("Protein Bar");
        proteinBar.setCost(new BigDecimal("275"));
        proteinBar.setInventoryCount(3);
        inventory.put(proteinBar.getName(), proteinBar);
        
        Item chips = new Item();
        chips.setName("Chips");
        chips.setCost(new BigDecimal("250"));
        chips.setInventoryCount(3);
        inventory.put(chips.getName(), chips);
        
        Item gatorade = new Item();
        gatorade.setName("Gatorade");
        gatorade.setCost(new BigDecimal("200"));
        gatorade.setInventoryCount(3);
        inventory.put(gatorade.getName(), gatorade);
        
        writeInventory();
    }
}
