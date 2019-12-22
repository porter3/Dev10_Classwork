package com.jakeporter.vendingmachine.ui;

import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author jake
 */
public class VendingMachineView {

    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public boolean displayMenu(List<Item> inventoryItems){
        // ensure at least 1 item is in stock
        long itemsInStock = inventoryItems.stream()
                .filter(item -> item.getInventoryCount() > 0)
                .count();
        // if there are items in stock, print them
        if (itemsInStock > 0){
            // display menu to user
            io.print("\n-----MENU-----");
            inventoryItems.stream()
                    .filter(item -> item.getInventoryCount() > 0)
                    .forEach(item -> {io.print(item.getName() + ": " 
                            + item.getCost().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) 
                            + ", " + item.getInventoryCount() + " in stock.");});
            return true;
        }
        else{
            io.print("Vending machine is out of items. Exiting program now.");
            return false;
        }
    }
    public BigDecimal getMoney(){
        while(true){
            String moneyString = io.readString("\nPlease enter your money(FORMAT: 0.00): ");
            // verify proper format
            try{
                // parse string to ensure it's actually in a number format
                Double.parseDouble(moneyString);
            }
            catch(NumberFormatException e){
                io.print("Please ensure you entered your money in the format 0.00");
                continue;
            }
            return new BigDecimal(moneyString);
        }
    }
    
    public String getSelection(){
        String selection = io.readString("Please enter your selection:");
        // convert selection to capitalized words
        // convert entered words to String array
        String[] selectionWords = selection.split(" ");
        String capitalizedSelection = "";
        for (int i = 0; i < selectionWords.length; i++){
            // take first character of word, convert to uppercase, concatenate to a lowercase version of the rest of the word
            selectionWords[i] = selectionWords[i].substring(0, 1).toUpperCase() + selectionWords[i].substring(1).toLowerCase();
            capitalizedSelection += selectionWords[i];
            capitalizedSelection += " ";
        }
        return capitalizedSelection.strip();
    }
    
    public void displaySuccessAndChangeReturned(Change change){
        io.print("Great job vending. Your total change is $" 
                + change.getTotalChangeInDollars().toString() + "\nYou're receiving:\n"
                    + change.getQuarters().toString() + " quarters\n" 
                    + change.getDimes().toString() + " dimes\n" 
                    + change.getNickels().toString() + " nickels\n"
                    + change.getPennies().toString() + " pennies");
    }
    
    public boolean promptToSelectAgain(){
        while(true){
            String choiceStr = io.readString("Would you like to select another item? (y/n)");
            if (choiceStr.charAt(0) == 'y'){
                return true;
            }
            else if (choiceStr.charAt(0) == 'n'){
                return false;
            }
        }
    }
    
    public boolean promptToExit(){
        // return false if user wants to exit
        while(true){
            String choiceString = io.readString("Would you put in more money or exit the program?\n"
                    + "1. Add more money\n" + "2. Exit");
            int choiceInt = 0;
            try{
                choiceInt = Integer.parseInt(choiceString);
            }
            catch(NumberFormatException e){
                
            }
            if ((choiceString.strip().contains("exit")) || choiceInt == 2){
                return false;
            }
            else if ((choiceString.strip().contains("add")) || (choiceString.strip().contains("money")) || choiceInt == 1){
                return true;
            }
            else{
                io.print("Command not recognized");
            }
        }
    }
    
    public void displayErrorMessage(String errorMessage){
        io.print("Error: " + errorMessage);
    }
}
