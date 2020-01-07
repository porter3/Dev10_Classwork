package com.jakeporter.fortimes;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("What times table shall I recite?  ");
        int timesTable = sc.nextInt();
        
        for (int i = 1; i <= 15; i++){
            System.out.printf("%d * %d is: %d\n", i, timesTable, i*timesTable);
        }
    }
}
