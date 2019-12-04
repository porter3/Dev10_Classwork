package com.jakeporter.fortimesfor;

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
        
        int userAnswer;
        int answer;
        int numberRight = 0;
        for (int i = 1; i <= 15; i++){
            System.out.printf("%d * %d is: ", i, timesTable);
            userAnswer = sc.nextInt();
            answer = i * timesTable;
            if (userAnswer == answer){
                System.out.println("Correct!");
                numberRight++;
            }
            else{
                System.out.printf("Sorry, the answer is: %d\n", answer);
            }
        }
        System.out.printf("You got %d correct.", numberRight);
    }
}
