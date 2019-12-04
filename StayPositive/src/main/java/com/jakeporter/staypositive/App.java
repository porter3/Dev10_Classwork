package com.jakeporter.staypositive;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Give me a number to count down from!  ");
        int countdown = sc.nextInt();
        int countToNewLine = 0;
        
        System.out.println("Here goes!");
        
        while (countdown >= 0){
            System.out.printf("%d ", countdown);
            countdown--;
            countToNewLine++;
            if (countToNewLine == 10){
                System.out.println();
                countToNewLine = 0;
            }
        }
        
    }
}
