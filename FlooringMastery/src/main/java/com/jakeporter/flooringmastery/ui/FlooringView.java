package com.jakeporter.flooringmastery.ui;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public class FlooringView {

    private UserIO io;
    
    FlooringView(UserIO io){
        this.io = io;
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
    
    public Order getNewOrder(List<Product> productList){
        // set all
        String customerName = io.readString("Customer name:");
        // handle exception here (InvalidStateException)
        String state = io.readString("State of installation (i.e. NC, WY, TX):").strip().toUpperCase();
        // handle exception here (NumberFormatException???)
        BigDecimal area = new BigDecimal (io.readString("Area in square feet:").strip());
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
        int materialSelection = io.readInt("Material number:", 1, productList.size());
        Order newOrder = new Order();
        newOrder.setCustomerName(customerName);
        newOrder.setArea(area);
        newOrder.setState(state);
        // set productType to the material chosen
        newOrder.setProductType(materials.get(materialSelection));
        return newOrder;
    }
    
    public void displayOrder(Order order){
        io.print("Order #" + order.getOrderNumber() + ":\n"
                + "Customer Name: " + order.getCustomerName() + "\n"
                + "State: " + order.getState() + "\n"
                + "Area(sq. ft.): " + order.getArea() + "\n"
                + "Flooring type: " + order.getProductType() + "\n");
    }
    
    public boolean promptToCommitOrder(){
        while(true){
            char choice = io.readString("Confirm order? (y/n)").toLowerCase().strip().charAt(0);
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
    
    public void displayAllOrders(List<Order> orderList){
        orderList.stream()
                .forEach(order -> io.print("Order #" + order.getOrderNumber()
                + "\nCustomer: " + order.getCustomerName()
                + "\nFlooring type: " + order.getProductType()
                + "\nArea(sq. ft.): " + order.getArea()
                + "\nFlooring cost: $" + order.getMaterialCost()
                + "\nLabor cost: $" + order.getLaborCost()
                + "\nTax: $" + order.getTotalTax()
                + "\nOrder total: $" + order.getOrderTotal() + "\n"));
    }
    
    public void displayExitMessage(){
        io.print("Goodbye");
    }
    
    public void displayUnknownCommand(){
        io.print("Unknown command, please select from the menu again");
    }
    
    public void displayErrorMessage(String message){
        io.print(message);
    }
}
