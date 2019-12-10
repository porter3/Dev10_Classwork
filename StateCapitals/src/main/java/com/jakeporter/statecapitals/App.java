package com.jakeporter.statecapitals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Map<String, String> stateCapitals = new HashMap();
        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska", "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");
        
        // get keys from map
        Set<String> keys = stateCapitals.keySet();
        
        System.out.println("STATES:");
        for (String currentKey : keys){
            //print each state name one by one
            System.out.println(currentKey);
        }
        
        System.out.println("CAPITALS:");
        for (String currentKey : keys){
            //print each capital out one by one
            System.out.println(stateCapitals.get(currentKey));
        }
        
        // use key set to get each value from the map one by one
        System.out.println("STATES AND CAPITALS:");
        for (String currentKey : keys){
            System.out.printf("%s - %s\n", currentKey, stateCapitals.get(currentKey));
        }
        
                
        
        
        
    }
}
