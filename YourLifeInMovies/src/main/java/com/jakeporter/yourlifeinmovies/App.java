package com.jakeporter.yourlifeinmovies;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Hey, let's play a game! What's your name?");
        String fullName = sc.nextLine();
        
        System.out.printf("Ok, %s, when were you born?\n", fullName);
        int birthYear = sc.nextInt();
        
        String firstName = fullName.split(" ")[0];
        
        System.out.printf("\nWell %s...\n", firstName);
        
        if (birthYear < 2005){
            System.out.println("Did you know that Pixar's 'Up' came out half a decade ago?");
        }
        if (birthYear < 1995){
            System.out.println("The first Harry Potter came out over 15 years ago!");
        }
        if (birthYear < 1985){
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        if (birthYear < 1975){
            System.out.println("The original Jurassic Park release is closer to the date of the first lunar landing than it is to today.");
        }
        if (birthYear < 1965){
            System.out.println("The MASH TV series has been around for almost half a century!");
        }
    }
}
