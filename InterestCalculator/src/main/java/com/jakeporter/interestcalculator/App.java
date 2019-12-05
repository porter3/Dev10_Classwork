package com.jakeporter.interestcalculator;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
                
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the annual interest rate:");
        double annualRate = sc.nextDouble();
        System.out.println("Please enter the initial principal amount:");
        double principal = sc.nextDouble();
        System.out.println("Please enter how many years you would like to calculate for:");
        double years = sc.nextDouble();
        System.out.println("\n");
        
        double eoyPrincipal = principal;
        //iterate over number of years
        for (double i = 1; i <= years; i++){
            //print year number
            System.out.printf("Year %.0f results:\n", i);
            //set beginning year principal to previous year principal and print
            principal = eoyPrincipal;
            System.out.printf("Beginning year principal: %.2f\n", principal);
            //calculate principal at end of year
            eoyPrincipal = interestCalcDoubles(principal, annualRate);
            //print total interest earned
            System.out.printf("Total interest earned: %.2f\n", eoyPrincipal - principal);
            //print EOY principal
            System.out.printf("End of year principal: %.2f\n\n", eoyPrincipal);
        }
    }
    
    public static double interestCalcDoubles(double principal, double annualRate){
        double quarterlyInterestRate = annualRate / 4;
        return principal * (1 +(quarterlyInterestRate / 100));
    }
}
