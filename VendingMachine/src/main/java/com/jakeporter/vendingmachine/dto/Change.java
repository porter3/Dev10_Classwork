package com.jakeporter.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jake
 */
public class Change {

    private final Map<Coins, BigDecimal> coins;
    private BigDecimal totalChangeInDollars;
    
    // if change is $5.00, argument should be "500"
    public Change(String pennies){
        coins = new HashMap();
        calculateChange(pennies);
    }
    
    private void calculateChange(String pennies){
        
        // total amount of change
        BigDecimal totalInPennies = new BigDecimal(pennies);
        // set total change value
        totalChangeInDollars = totalInPennies.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        
        // calculate remainder of change (in pennies) after quarters have been taken out
        BigDecimal remainderInPennies = totalInPennies.remainder(Coins.QUARTERS.getValue());
        // calculate amount of quarters that fit into the total
        BigDecimal quarterCount = totalInPennies.subtract(remainderInPennies).divide(Coins.QUARTERS.getValue());
        this.coins.put(Coins.QUARTERS, quarterCount);
        
        // change previous total to current total
        totalInPennies = remainderInPennies; // 15
        // calculate remainder after dimes are taken out
        remainderInPennies = totalInPennies.remainder(Coins.DIMES.getValue()); // 5
        // calculate amount of dimes that fit into total
        BigDecimal dimeCount = totalInPennies.subtract(remainderInPennies).divide(Coins.DIMES.getValue());
        this.coins.put(Coins.DIMES, dimeCount);
        
        // nickels
        totalInPennies = remainderInPennies;
        remainderInPennies = totalInPennies.remainder(Coins.NICKELS.getValue());
        BigDecimal nickelCount = totalInPennies.subtract(remainderInPennies).divide(Coins.NICKELS.getValue());
        this.coins.put(Coins.NICKELS, nickelCount);
        
        // pennies
        this.coins.put(Coins.PENNIES, remainderInPennies);
    }

    public Map<Coins, BigDecimal> getCoins() {
        return coins;
    }
    
    public BigDecimal getQuarters(){
        return coins.get(Coins.QUARTERS);
    }
    
    public BigDecimal getDimes(){
        return coins.get(Coins.DIMES);
    }
    
    public BigDecimal getNickels(){
        return coins.get(Coins.NICKELS);
    }
    
    public BigDecimal getPennies(){
        return coins.get(Coins.PENNIES);
    }

    public BigDecimal getTotalChangeInDollars() {
        return totalChangeInDollars;
    }
}
