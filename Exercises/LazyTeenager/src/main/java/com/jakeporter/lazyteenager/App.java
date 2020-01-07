package com.jakeporter.lazyteenager;

import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Random chanceCheck = new Random();
        
        int chanceOfCleaning = 5;
        int counter = 1;
        
        do{
            System.out.printf("Clean your room! x%d\n", counter);
            chanceOfCleaning += 5;
            counter++;
            
            if (counter == 16){
                System.out.println("That's it, I'm doing it! You're grounded!");
            }
        }while (chanceCheck.nextInt(101) >= chanceOfCleaning);
    }
}
