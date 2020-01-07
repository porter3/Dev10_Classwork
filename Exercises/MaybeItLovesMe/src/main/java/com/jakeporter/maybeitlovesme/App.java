package com.jakeporter.maybeitlovesme;

import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Random rndm = new Random();
        //formula for creating random numbers within a custom range
        int petals = rndm.nextInt((89 - 13) + 1) + 13;
        int counter = rndm.nextInt();
        
        while (petals > 13){
            if (counter % 2 == 0){
                System.out.println("It LOVES me NOT!");
            }
            else{
                System.out.println("It LOVES me!");
            }
            petals--;
            counter--;
        }
        if (counter % 2 != 0){
            System.out.println("Awww, bummer.");
        }
    }
}
