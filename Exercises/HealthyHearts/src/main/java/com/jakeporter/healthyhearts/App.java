package com.jakeporter.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("What is your age?  ");
        int age = sc.nextInt();
        
        int maxHr = 220 - age;
        float targetHrUpper = (float) .85 * maxHr;
        float targetHrLower = (float) .5 * maxHr;
        
        System.out.printf("Your maximum heart rate should be %d beats per minute.\n", maxHr);
        System.out.printf("Your target HR Zone is %.1f - %.1f per minute.", targetHrLower, targetHrUpper);
    }
}
