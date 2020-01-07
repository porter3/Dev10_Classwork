package com.jakeporter.highroller;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class HighRoller {
    public static void main(String[] args){
        
        Random roller = new Random();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many sides would you like on your die?");
        int sides = sc.nextInt();
        
        //tell user to roll the dice
        System.out.print("\nHit 'Enter' to roll the die!");
        sc.nextLine();
        sc.nextLine();
        
        int rollResult = roller.nextInt(sides + 1) + 1;
        
        System.out.println("Time to roll the die!");
        System.out.printf("You rolled %d.", rollResult);
        
        if (rollResult == (sides)){
            System.out.println("You rolled a critical! Nice job!");
        }
        else if (rollResult == 1){
            System.out.println("You rolled a critical failure!");
        }
    }
}
