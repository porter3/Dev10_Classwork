package com.jakeporter.doitbetter;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many miles can you run?");
        int miles = sc.nextInt();
        System.out.printf("That's nothing, I can run %d miles.\n\n", miles*2+1);
        
        System.out.println("How many hotdogs can you eat?");
        int hotdogs = sc.nextInt();
        System.out.printf("That's nothing, I can eat %d hotdogs.\n\n", hotdogs*2+1);
        
        System.out.println("How many languages do you know?");
        int languages = sc.nextInt();
        System.out.printf("That's nothing, I know %d languages.\n\n", languages*2+1);
    }
}
