package com.jakeporter.flooringmastery.ui;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author jake
 */
public class FlooringView {

    private UserIO io;
    
    FlooringView(UserIO io){
        this.io = io;
    }
    
    public int promptMode(){
        return io.readInt("CHOOSE MODE\n"
        + "1. Production\n2. Training (save function will be disabled)", 1, 2);
    }

    public int printMenuAndGetInput() {
        return io.readInt("\nMENU\n----\n"
                + "Please select an operation to perform:\n"
                + "1. Display Orders\n"
                + "2. Add New Order\n"
                + "3. Edit Existing Order\n"
                + "4. Delete Order\n"
                + "5. Save Current Work\n"
                + "6. Quit", 1, 6);
    }
    
    public Order getNewOrder(List<Product> productList, Set<String> stateList){
        // set all
        String dateString;
        LocalDate orderDate;
        // convert dateString to LocalDate, check for proper formatting
        while(true){
            /* although regex is used to get a date, I'm keeping the try-catch block in case the regexes in convertToLocalDatePattern
            don't catch whatver the user input is*/
            try{
                // get user input for date, allow for any symbol between month/day/year, or no symbol at all
                dateString = io.readString("\nDate of order (mm/dd/yyyy):").strip();
                // check if user's input looks like a legit date, prompt again for input if it doesn't
                if(!Pattern.matches("\\d\\d\\W?\\d\\d\\W?\\d\\d\\d\\d", dateString)){
                    io.print("Date must be in the format mm/dd/yyyy (or something very similar).");
                    continue;
                }
                // method implementation below, converts user's input to 'MM/dd/yyyy' pattern
                dateString = convertToLocalDatePattern(dateString);
                // convert user's input to a LocalDate
                orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                if (orderDate.compareTo(LocalDate.now()) < 0){
                    io.print("Date of order cannot be earlier than today.");
                    continue;
                }
                break;
            }
            catch(DateTimeException e){
                io.print("Date is not a proper date.");
            }
        }
        String customerName = io.readString("Customer name:");
        String state;
        while(true){
            state = io.readString("State of installation (i.e. NC, WY, TX):").strip().toUpperCase();
            if(stateList.contains(state)){
                break;
            }
            io.print("State does not exist.\n");
        }
        BigDecimal area;
        while(true){
            try{
                area = new BigDecimal (io.readString("Area in square feet:").strip());
                break;
            }
            catch(NumberFormatException e){
                io.print("Area must be a number.");
            }
        }
        // allow user to select material
        Map<Integer, String> materials = new HashMap();
        io.print("Material ordered:");
        // iterate through productList
        for (int i = 0; i < productList.size(); i++){
            // print product and listing number
            io.print(Integer.toString(i+1) + ". " + productList.get(i).getProductType());
            // assign productType and listing number to hashmap
            materials.put(i+1, productList.get(i).getProductType());
        }
        int materialSelection = io.readInt("Select a choice:", 1, productList.size());
        Order newOrder = new Order();
        newOrder.setCustomerName(customerName);
        newOrder.setArea(area);
        newOrder.setState(state);
        newOrder.setDateCreated(orderDate);
        // set productType to the material chosen
        newOrder.setProductType(materials.get(materialSelection));
        return newOrder;
    }
    
    private String convertToLocalDatePattern(String dateString){
        // if dateString matches \d\d\D\d\d\D\d\d\d\d
        if (Pattern.matches("\\d\\d\\W\\d\\d\\W\\d\\d\\d\\d", dateString)){
            return dateString.replaceAll("\\W", "-");
        }
        if (Pattern.matches("\\d\\d\\d\\d\\d\\d\\d\\d", dateString)){
            return dateString.substring(0, 2) + "-" + dateString.substring(2, 4) + "-" + dateString.substring(4, 8);
        }
        else{
            throw new DateTimeException("Date input could not be parsed.");
        }
    }
    
    public void displayOrder(Order order){
        io.print("Order #" + order.getOrderNumber() + ":\n"
                + "Date: " + order.getDateCreated().toString() + "\n"
                + "Customer Name: " + order.getCustomerName() + "\n"
                + "State: " + order.getState() + "\n"
                + "Area(sq. ft.): " + order.getArea() + "\n"
                + "Flooring type: " + order.getProductType() + "\n");
    }
    
    public boolean promptToCommitOrder(){
        while(true){
            char choice = io.readString("\nConfirm order? (y/n)").toLowerCase().strip().charAt(0);
            if (choice == 'y'){
                io.print("Order confirmed.");
                return true;
            }
            else if (choice == 'n'){
                io.print("Order discarded.");
                return false;
            }
            else{
                io.print("Command not recognized.");
            }
        }
    }
    
    public int printDisplayMenuAndGetInput(){
        return io.readInt("\nDISPLAY MENU\n------------\n"
        + "1. View all orders\n"
        + "2. View all orders of a specific date", 1, 2);
    }
    
    public void displayAllOrders(List<Order> orderList){
        orderList.stream()
                .forEach(order -> io.print("\nOrder #" + order.getOrderNumber()
                + "\nDate: " + order.getDateCreated().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                + "\nCustomer: " + order.getCustomerName()
                + "\nState: " + order.getState()
                + "\nFlooring type: " + order.getProductType()
                + "\nArea(sq. ft.): " + order.getArea()
                + "\nFlooring cost: $" + order.getMaterialCost()
                + "\nLabor cost: $" + order.getLaborCost()
                + "\nTax: $" + order.getTotalTax()
                + "\nOrder total: $" + order.getOrderTotal()));
    }
    
    public LocalDate getDateForDisplaying(){
        String dateString;
        while(true){
            try{
                dateString = io.readString("\nEnter the date you would like to view orders for (mm/dd/yyyy):");
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            catch(DateTimeException e){
                io.print("Date must be a proper date in the format 'mm/dd/yyyy'");
            }
        }
    }
    
    public void displayOrdersOfDate(LocalDate date, List<Order> allOrders){
        io.print("\nOrders For " + date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        allOrders.stream()
                .filter(order -> (order.getDateCreated().compareTo(date) == 0))
                .forEach(order -> io.print("\nOrder #" + order.getOrderNumber()
                + "\nCustomer: " + order.getCustomerName()
                + "\nState: " + order.getState()
                + "\nFlooring type: " + order.getProductType()
                + "\nArea(sq. ft.): " + order.getArea()
                + "\nFlooring cost: $" + order.getMaterialCost()
                + "\nLabor cost: $" + order.getLaborCost()
                + "\nTax: $" + order.getTotalTax()
                + "\nOrder total: $" + order.getOrderTotal() + "\n"));
    }
    
    public void displayEditBanner(){
        io.print("\nEdit an Order");
    }
    
    public LocalDate getDateForEditing(){
        String dateString;
        while(true){
            try{
                dateString = io.readString("\nPlease enter the date of the order you want to edit(mm/dd/yyyy):");
                if (!Pattern.matches("\\d\\d\\W?\\d\\d\\W?\\d\\d\\d\\d", dateString)){
                    io.print("Date must be in the format mm/dd/yyyy (or something very similar).");
                    continue;
                }
                dateString = convertToLocalDatePattern(dateString);
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            }
            catch(DateTimeException e){
                io.print("Date is not a proper date.");
            }
        }
    }
    
    public String getOrderNumber(LocalDate orderDate){
        int orderNumber = io.readInt("\nPlease enter order number for " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return String.format("%05d", orderNumber);
    }
    
    public Order getEditedOrder(Order orderToEdit, List<Product> productList, Set<String> states){
        io.print("\nEdit order fields (hit 'return' to keep field the same):");
        String newDateStr;
        LocalDate newDate;
        while(true){
            newDateStr = io.readString("Current Order Date: " + orderToEdit.getDateCreated().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    + " (new dates should be entered as mm/dd/yyyy)").strip();
            // if user leaves a field blank by hitting 'return', just assign it to the old field
            if (newDateStr.isBlank()){
                newDate = orderToEdit.getDateCreated();
                break;
            }
            else{
                try{
                    if(!Pattern.matches("\\d\\d\\W?\\d\\d\\W?\\d\\d\\d\\d", newDateStr)){
                        io.print("Date must be in the format mm/dd/yyyy (or something very similar).");
                        continue;
                    }
                    newDateStr = convertToLocalDatePattern(newDateStr);
                    newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    break;
                }
                catch(NumberFormatException e){
                    io.print("Date is not a proper date.");
                }
            }
        }
        String newCustomerName = io.readString("Current customer info: " + orderToEdit.getCustomerName()).strip();
        if (newCustomerName.isBlank()){
            newCustomerName = orderToEdit.getCustomerName();
        }
        // state
        String newState;
        while(true){
            newState = io.readString("Current state: " + orderToEdit.getState()).strip().toUpperCase();
            if (newState.isBlank()){
                newState = orderToEdit.getState();
                break;
            }
            if (states.contains(newState)){
                break;
            }
            io.print("State does not exist.\n");
        }
        // area
        String newAreaStr;
        BigDecimal newArea;
        while (true){
            newAreaStr = io.readString("Current area(sq. ft.): " + orderToEdit.getArea()).strip();
            // if user hits 'return', assign old area value to the new area field
            if (newAreaStr.isBlank()){
                newArea = orderToEdit.getArea();
                break;
            }
            // if user enters something:
            else{
                try{
                    // area is set to what the user entered
                    newArea = new BigDecimal(newAreaStr);
                    // if area is <= 0, prompt user to enter positive value and go back to top of loop
                    if (newArea.compareTo(new BigDecimal("0")) == 0 || newArea.compareTo(new BigDecimal("0")) == -1){
                        io.print("Please enter a positive number for the area.");
                        continue;
                    }
                    break;
                }
                // if user didn't enter a number
                catch(NumberFormatException e){
                    io.print("Please enter a valid number or hit return to keep this field the same.");
                }
            }
        }
        // material
        // allow user to select material
        Map<Integer, String> materials = new HashMap();
        io.print("Material ordered:");
        // iterate through productList
        for (int i = 0; i < productList.size(); i++){
            // print product and listing number
            io.print(Integer.toString(i+1) + ". " + productList.get(i).getProductType());
            // assign productType and listing number to hashmap
            materials.put(i+1, productList.get(i).getProductType());
        }
        // allow user to keep previous material selection
        int sameMaterialOption = productList.size() + 1;
        io.print(sameMaterialOption + ". Keep previous material");
        int materialSelection = io.readInt("Select a choice:", 1, sameMaterialOption);
        
        // create new 'edited' order, assign values
        Order editedOrder = new Order();
        // keep the edited order's order number the same
        editedOrder.setOrderNumber(orderToEdit.getOrderNumber());
        // set all other values
        editedOrder.setDateCreated(newDate);
        editedOrder.setCustomerName(newCustomerName);
        editedOrder.setState(newState);
        editedOrder.setArea(newArea);
        /* if user selected the same material option earlier, 
        assign the old material value to the edited order's field */
        if (materialSelection == sameMaterialOption){
            editedOrder.setProductType(orderToEdit.getProductType());
        }
        else{
            editedOrder.setProductType(materials.get(materialSelection));
        }
        return editedOrder;
    }
    
    public void displayDeleteBanner(){
        io.print("\nDelete an Order");
    }
    
    public LocalDate getDateForDeletion() {
        String dateString;
        while(true){
            try{
                dateString = io.readString("Please enter the date of the order you want to delete(mm/dd/yyyy):");
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            catch(DateTimeException e){
                io.print("Date must be a proper date in the format 'mm/dd/yyyy'");
            }
        }
    }
    
    public boolean promptDeletionConfirmation(Order order){
        io.print("Order for deletion: ");
        displayOrder(order);
        while(true){
            char choice = io.readString("Delete order? (y/n)").toLowerCase().strip().charAt(0);
            if (choice == 'y'){
                io.print("Order confirmed.");
                return true;
            }
            else if (choice == 'n'){
                io.print("Order discarded.");
                return false;
            }
            else{
                io.print("Command not recognized.");
            }
        }
    }
    
    public void displayDeletionSuccess(Order deletedOrder){
        io.print("Order #" + deletedOrder.getOrderNumber() + " successfully deleted");
    }
    
    public void displaySaveSuccess(){
        io.print("Orders saved successfully.");
    }
    
    public void displayFakeSaveSuccess(){
        io.print("(TRAINING MODE) Orders not saved.");
    }
    
    public void displayExitMessage(){
        io.print("\nGoodbye");
    }
    
    public void displayUnknownCommand(){
        io.print("\nUnknown command, please select from the menu again");
    }
    
    public void displayErrorMessage(String message){
        io.print(message);
    }

 }
