package com.jakeporter.alittlechaos;

import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Random rndm = new Random();
        
        System.out.println("Random can make integers: " + rndm.nextInt());
        System.out.println("Or a double: " + rndm.nextDouble());
        System.out.println("Or even a boolean: " + rndm.nextBoolean());
        
        int num = rndm.nextInt(100);
        
        System.out.printf("You can store a randomized result: %d\n", num);
        System.out.printf("And use it over and over again: %d, %d\n", num, num);
        
        System.out.println("Or just keep generating new values");
        System.out.println("Here's a bunch of numbers from 0 - 100: ");
        
        System.out.printf("%d, %d, %d, %d, %d, %d", rndm.nextInt(101), 
                rndm.nextInt(101), rndm.nextInt(101), rndm.nextInt(101), 
                rndm.nextInt(101), rndm.nextInt(101));
    }
}
