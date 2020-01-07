package com.jakeporter.biggerbetteradder;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter three integers:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        
        int sum = a + b + c;
        
        System.out.println(sum);
        System.out.println(sum);
    }
}
