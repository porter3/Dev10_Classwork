package com.jakeporter.flooringmastery.ui;

import com.jakeporter.flooringmastery.dto.Order;
import java.math.BigDecimal;

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
        return io.readInt("MENU\n----\n"
                + "Please select an operation to perform:\n"
                + "1. Display Orders By Date\n"
                + "2. Add New Order\n"
                + "3. Edit Existing Order\n"
                + "4. Delete Order\n"
                + "5. Save Current Work\n"
                + "6. Quit", 1, 6);
    }
    
    public Order getNewOrder(){
        // set all
        String customerName = io.readString("Customer name:");
        // handle exception here (InvalidStateException)
        String state = io.readString("State of installation (i.e. NC, WY, TX):").strip().toUpperCase();
        // handle exception here (NumberFormatException???)
        BigDecimal area = new BigDecimal (io.readString("Area in square feet:").strip());
        Order newOrder = new Order();
        newOrder.setCustomerName(customerName);
        newOrder.setArea(area);
        newOrder.setState(state);
        return newOrder;
    }
    
    public void displayExitMessage(){
        io.print("Goodbye");
    }
    
    public void displayUnknownCommand(){
        io.print("Unknown command, please select from the menu again");
    }
}
