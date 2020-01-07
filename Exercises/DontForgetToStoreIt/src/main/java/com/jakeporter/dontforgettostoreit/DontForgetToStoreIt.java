/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.dontforgettostoreit;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class DontForgetToStoreIt {
    public static void main(String[] args){
        int meaningOfLife = 42;
        double pi = 3.14159;
        String cheese, color;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Give me pi to at least 5 decimals: ");
        
        pi = sc.nextDouble();
        
        System.out.println("What is the meaning of life? ");
        meaningOfLife = sc.nextInt();
        
        System.out.println("What is your favorite kind of cheese?");
        cheese = sc.next();
        
        System.out.println("Do you like red or blue? ");
        color = sc.next();
        
        System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
        System.out.println("The circumference of life is " +( 2 * pi * meaningOfLife));
    }
}
