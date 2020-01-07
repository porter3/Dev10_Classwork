package com.jakeporter.factorizer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> primeFactors = new ArrayList();
        
        System.out.print("Please enter a number you would like to analyze:    ");
        int userNumber = sc.nextInt();
        System.out.printf("The factors of %d are:\n", userNumber);
        
        int sumOfFactors = 0;
        for (int i = 1; i <= userNumber; i++){
            if (userNumber % i == 0){
                primeFactors.add(i);
                // do not add the user's number to the sum of its factors
                if (i != userNumber){
                    sumOfFactors += i;
                    System.out.println(i);
                }
            }
        }
        if (sumOfFactors == userNumber){
            System.out.printf("%d is a perfect number.\n", userNumber);
        }
        else{
            System.out.printf("%d is not a perfect number.\n", userNumber);
        }
        
        if (primeFactors.size() == 2){
            System.out.printf("%d is a prime number.\n", userNumber);
        }
        else{
            System.out.printf("%d is not a prime number.", userNumber);
        }
        
    }
}
