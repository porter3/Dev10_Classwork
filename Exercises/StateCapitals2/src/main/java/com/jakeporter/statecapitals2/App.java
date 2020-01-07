package com.jakeporter.statecapitals2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        // create hashmap to hold all capital objects
        Map<String, Capital> capitals = new HashMap<>();
        
        Capital alabama = new Capital("Montgomery", 205, 156);
        Capital alaska = new Capital("Juneau", 31, 3225);
        Capital arizona = new Capital("Phoenix", 1445, 517);
        Capital arkansas = new Capital("Arkansas", 193, 116);
        
        capitals.put("Alabam", alabama);
        capitals.put("Alaska", alaska);
        capitals.put("Arizona", arizona);
        capitals.put("Arkansas", arkansas);
        
        Set<String> keySet = capitals.keySet();
        
        for (String state : keySet){
            System.out.printf("%s - Capital: %s - Pop: %d - Area: %d\n", state, capitals.get(state).getName(),
                    capitals.get(state).getPopulation(), capitals.get(state).getSqMileage());
        }
    }
}
