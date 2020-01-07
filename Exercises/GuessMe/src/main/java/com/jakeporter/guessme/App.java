package com.jakeporter.guessme;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Random rndm = new Random();
        
        int pickedInt = 12;
        System.out.print("I've chosen a number between 1 and 20, bet you can't guess it!  ");
        int guess = sc.nextInt();
        
        System.out.printf("Your guess: %d\n\n", guess);
        
        if (guess > pickedInt){
            System.out.printf("Too bad, way too high. I chose %d.", pickedInt);
        }
        else if (guess < pickedInt){
            System.out.printf("Ha, nice try - too low! I chose %d.", pickedInt);
        }
        else{
            System.out.println("Wow, nice guess! That was it!");
        }
    }
}
