package com.jakeporter.traditionalfizzbuzz;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("How many fizzing and buzzing units would you like?  ");
        int limit = sc.nextInt();
        
        for (int i = 0; i <= limit; i++){
            if (i % 3 == 0){
                System.out.print("fizz ");
            }
            if (i % 5 == 0){
                System.out.print("buzz");
            }
            if ((i % 3 != 0) && (i % 5 != 0)){
                System.out.print(i);
            }
            System.out.println("");
        }
        System.out.println("TRADITION!");
    }
}
