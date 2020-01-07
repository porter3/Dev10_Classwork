package com.jakeporter.guessmemore;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Random rndm = new Random();
        
        boolean negativeRandomizer = rndm.nextBoolean();
        int randomNumber = rndm.nextInt(101);
        if (negativeRandomizer == false){
            randomNumber -= 100;
        }
        
        int guess = 101;
        do{
        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!");
        guess = sc.nextInt();
        
            if (guess < randomNumber){
                System.out.println("Too low, try again!");
            }
            else if (guess > randomNumber){
            System.out.println("Too high, try again!");
            }
            else{
                System.out.println("You got it right!");
            }
        }
        while (guess != randomNumber);
    }
}
