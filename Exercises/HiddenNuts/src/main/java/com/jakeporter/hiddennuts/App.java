package com.jakeporter.hiddennuts;

import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        String[] hidingSpots = new String[100];
        Arrays.fill(hidingSpots, "a");
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length) + 1] = "Nut";
        System.out.println("The nut has been hidden...");
        
        String check = "Nut";
        for (int i = 0; i < hidingSpots.length; i++){
            if (check.compareTo(hidingSpots[i]) == 0){
                System.out.printf("Found it! It's in spot #%d.", i);
            }
        }
    }
}
