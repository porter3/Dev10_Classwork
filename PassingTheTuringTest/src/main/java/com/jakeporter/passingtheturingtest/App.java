package com.jakeporter.passingtheturingtest;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
   public static void main(String[] args){
       
       Scanner sc = new Scanner(System.in);
       
       System.out.print("Hello there!" + "\n" + "What's your name?  ");
       String name = sc.nextLine();
       
       System.out.printf("Hi, %s! What's your favorite color?  ", name);
       String color = sc.nextLine();
       
       System.out.printf("\nHuh, %s? Mine's Electric Lime.\n\n", color);
       System.out.printf("I really like limes. They're my favorite fruit too.\n" + "What's YOUR favorite fruit, %s?  ", name);
       String fruit = sc.nextLine();
       
       System.out.printf("\nReally? %s? That's wild!\n", fruit);
       System.out.print("Speaking of favorites, what's your favorite number?  ");
       int favoriteNumber = sc.nextInt();
       
       int productOfNegSeven = favoriteNumber * -7;
       System.out.printf("\n%d is a cool number. Mine's -7.\nDid you know %d * -7 is %d? That's a cool number too!\n\n",
               favoriteNumber, favoriteNumber, productOfNegSeven);
               
       System.out.printf("Well, thanks for talking to me %s!", name);
   }
}
