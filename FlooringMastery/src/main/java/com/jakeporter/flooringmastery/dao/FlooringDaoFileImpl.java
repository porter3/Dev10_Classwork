package com.jakeporter.flooringmastery.dao;

import com.jakeporter.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final String DELIMITER = "::";
    private final String TAX_RATE_FILE = "stateTaxes.txt";

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
    
    
}
