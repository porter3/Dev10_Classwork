package com.jakeporter.flooringmastery.dto;

import java.util.Comparator;

/**
 *
 * @author jake
 */
public class OrderComparator implements Comparator<Order>{
    
    @Override
    public int compare(Order orderA, Order orderB){
        int a = Integer.parseInt(orderA.getOrderNumber());
        int b = Integer.parseInt(orderB.getOrderNumber());
        if (a > b){
            return 1;
        }
        if (b > a){
            return -1;
        }
        else{
            return 0;
        }
    }
}