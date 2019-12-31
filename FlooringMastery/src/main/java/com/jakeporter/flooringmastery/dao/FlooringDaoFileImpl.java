package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import com.jakeporter.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class FlooringDaoFileImpl implements FlooringDao{
    
    private Map<String, Order> orders = new HashMap();
    private Map<String, String> stateTaxRates = new HashMap();
    private Map<String, Product> productInfoMap = new HashMap();
    private final String DELIMITER = "::";
    private final String TAX_RATE_FILE = "stateTaxes.txt";
    private final String PRODUCT_FILE = "productInfo.txt";

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
}
