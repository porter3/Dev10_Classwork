package com.jakeporter.interestcalculatorrefactored;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class InterestCalculator {
    
    double annualRate;
    double principal;
    double years;

    public void setVariables(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the annual interest rate:");
        this.annualRate = sc.nextDouble();
        System.out.println("Please enter the initial principal amount:");
        this.principal = sc.nextDouble();
        System.out.println("Please enter how many years you would like to calculate for:");
        this.years = sc.nextDouble();
        System.out.println("\n");
    }
    
    public void calculateAndPrintResults(){
        double eoyPrincipal = this.principal;
        for (double i = 1; i <= this.years; i++){
            //print year number
            System.out.printf("Year %.0f results:\n", i);
            //set beginning year principal to previous year principal and print
            this.principal = eoyPrincipal;
            System.out.printf("Beginning year principal: %.2f\n", principal);
            //calculate principal at end of year
            eoyPrincipal = calculateNewPrincipal(this.principal, this.annualRate);
            //print total interest earned
            System.out.printf("Total interest earned: %.2f\n", eoyPrincipal - this.principal);
            //print EOY principal
            System.out.printf("End of year principal: %.2f\n\n", eoyPrincipal);
        }
    }
    
    public double calculateNewPrincipal(double principal, double annualRate){
        double quarterlyInterestRate = annualRate / 4;
        return principal * (1 +(quarterlyInterestRate / 100));
    }
}
