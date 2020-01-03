package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class FlooringDaoFileImpl implements FlooringDao{
    
    private Map<String, Order> orders = new HashMap();
    private Map<String, String> stateTaxRates = new HashMap();
    private Map<String, Product> productInfoMap = new HashMap();
    private final String DELIMITER = "::";
    private final String TAX_RATE_FILE = "stateTaxes.txt";
    private final String PRODUCT_FILE = "productInfo.txt";
    private final String CONFIG_FILE = "config.txt";
    static String ORDER_FILE = "orders.txt";

    @Override
    public List<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList(orders.values());
        return orderList;
    }

    @Override
    public BigDecimal getTaxRate(String state) {
        return new BigDecimal(stateTaxRates.get(state));
    }
    
    @Override
    public void loadProductInfo() throws TaxPersistenceException{
        Scanner sc;
        
        try{
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        }
        catch(FileNotFoundException e){
            throw new TaxPersistenceException("Could not load product information");
        }
        String currentLine;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            String[] productInfo = currentLine.split(DELIMITER);
            Product newProduct = new Product();
            newProduct.setProductType(productInfo[0]);
            newProduct.setCostPerSquareFoot(new BigDecimal(productInfo[1]));
            newProduct.setLaborCostPerSquareFoot(new BigDecimal(productInfo[2]));
            productInfoMap.put(newProduct.getProductType(), newProduct);
        }
        sc.close();
    }

    @Override
    public void loadTaxRates() throws TaxPersistenceException{
        Scanner sc;
        
        try{
            sc = new Scanner(new BufferedReader(new FileReader(TAX_RATE_FILE)));
        }
        catch(FileNotFoundException e){
            throw new TaxPersistenceException("Could not load tax rates");
        }
        String currentLine;
        while(sc.hasNextLine()){
            // get next line
            currentLine = sc.nextLine();
            // split line at delimiter
            String [] infoArray = currentLine.split(DELIMITER);
            // assign each value to map
            stateTaxRates.put(infoArray[0], infoArray[1]);
        }
        sc.close();
    }
    
    @Override
    public Map<String, Product> getAllProducts(){
        return productInfoMap;
    }
    
    @Override
    public Order addOrder(Order order){
        return orders.put(order.getOrderNumber(), order);
    }
    
    @Override
    public Order deleteOrder(Order order){
        return orders.remove(order.getOrderNumber());
    }

    @Override
    public boolean checkIfTrainingMode() throws ConfigurationPersistenceException, UnknownConfigurationException{
        // create Scanner to read file
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(CONFIG_FILE)));
        }
        catch(FileNotFoundException e){
            throw new ConfigurationPersistenceException("Could not load config file");
        }
        // STERETCH GOAL: use sc.next() with regex
        String[] configArray = sc.nextLine().split(DELIMITER);
        String currentMode = configArray[1];
        if (currentMode.equals("TRAINING")){
            return true;
        }
        else if (currentMode.equals("PRODUCTION")){
            return false;
        }
        else{
            throw new UnknownConfigurationException("Configuration mode not recognized");
        }
    }

    @Override
    public void setConfig(int configValue) throws ConfigurationPersistenceException {
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(CONFIG_FILE));
            // write configuration prefix
            writer.write("Mode" + DELIMITER);
            if (configValue == 1){
                writer.write("PRODUCTION");
            }
            else if (configValue == 2){
                writer.write("TRAINING");
            }
            else{
                throw new ConfigurationPersistenceException("Configuration input does not have an associated value");
            }
            writer.close();
        }
        catch(IOException e){
            throw new ConfigurationPersistenceException("Could not write to config file");
        }
    }
    
    private String marshallOrder(Order unmarshalledOrder){
        return unmarshalledOrder.getOrderNumber() + DELIMITER
                + unmarshalledOrder.getDateCreated() + DELIMITER
                + unmarshalledOrder.getCustomerName() + DELIMITER
                + unmarshalledOrder.getState() + DELIMITER
                + unmarshalledOrder.getTaxInfo().getTaxRate() + DELIMITER
                + unmarshalledOrder.getProductType() + DELIMITER
                + unmarshalledOrder.getProductInfo().getCostPerSquareFoot() + DELIMITER
                + unmarshalledOrder.getProductInfo().getLaborCostPerSquareFoot() + DELIMITER
                + unmarshalledOrder.getArea() + DELIMITER
                + unmarshalledOrder.getMaterialCost() + DELIMITER
                + unmarshalledOrder.getLaborCost() + DELIMITER
                + unmarshalledOrder.getTotalTax() + DELIMITER
                + unmarshalledOrder.getOrderTotal() + DELIMITER;
    }
    
    private Order unmarshallOrder(String marshalledOrder){
        String[] orderInfoFields = marshalledOrder.split(DELIMITER);
        Order unmarshalledOrder = new Order();
        unmarshalledOrder.setOrderNumber(orderInfoFields[0]);
        unmarshalledOrder.setDateCreated(LocalDate.parse(orderInfoFields[1]));
        unmarshalledOrder.setCustomerName(orderInfoFields[2]);
        unmarshalledOrder.setState(orderInfoFields[3]);
        unmarshalledOrder.setTaxRate(new BigDecimal(orderInfoFields[4]));
        unmarshalledOrder.setProductType(orderInfoFields[5]);
        unmarshalledOrder.setCostPerSquareFoot(new BigDecimal(orderInfoFields[6]));
        unmarshalledOrder.setLaborCostPerSquareFoot(new BigDecimal(orderInfoFields[7]));
        unmarshalledOrder.setArea(new BigDecimal(orderInfoFields[8]));
        unmarshalledOrder.setMaterialCost(new BigDecimal(orderInfoFields[9]));
        unmarshalledOrder.setLaborCost(new BigDecimal(orderInfoFields[10]));
        unmarshalledOrder.setTotalTax(new BigDecimal(orderInfoFields[11]));
        unmarshalledOrder.setOrderTotal(new BigDecimal(orderInfoFields[12]));
        return unmarshalledOrder;
    }
    
    @Override
    public void writeOrders() throws OrderPersistenceException{
        PrintWriter writer;
        
        try{
            writer = new PrintWriter(new FileWriter(ORDER_FILE));
        }
        catch(IOException e){
            throw new OrderPersistenceException("Could not save orders");
        }
        String orderAsString;
        List<Order> orderList = getAllOrders();
        for (Order unmarshalledOrder : orderList){
            orderAsString = marshallOrder(unmarshalledOrder);
            writer.println(orderAsString);
            writer.flush();
        }
        writer.close();
    }
    
    @Override
    public void loadOrders() throws OrderPersistenceException{
        Scanner sc;
        
        try{
            sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        }
        catch(FileNotFoundException e){
            throw new OrderPersistenceException("Could not load orders from file");
        }
        String currentLine;
        Order loadedOrder;
        while(sc.hasNextLine()){
            // get next line
            currentLine = sc.nextLine();
            // unmarshall it into an Order object
            loadedOrder = unmarshallOrder(currentLine);
            orders.put(loadedOrder.getOrderNumber(), loadedOrder);
        }
        sc.close();
    }
}
