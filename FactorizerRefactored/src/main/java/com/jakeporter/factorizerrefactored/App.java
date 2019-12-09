package com.jakeporter.factorizerrefactored;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
    Scanner sc = new Scanner(System.in);
    System.out.print("Please enter a number you would like to analyze:    ");

    Factorizer factorizer = new Factorizer(sc.nextInt());
        
    // generate list of factors
    factorizer.listPrimeFactors();
    
    System.out.printf("The factors of %d are:\n", factorizer.getNumToCheck());
    // print each number in primeList
    factorizer.printPrimeFactors();
    
    // add up all the factors
    factorizer.sumPrimeFactors();
    // print if number is perfect
    factorizer.printPerfectStatus();
    // print if number is prime
    factorizer.printPrimeStatus();
    }
}
