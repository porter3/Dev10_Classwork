package com.jakeporter.simplecalculator;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        while(true){
            // give choice of operations
            System.out.println("Would you like to add, subtract, multiply, or divide?");
            // take user input for choice of operations
            String operation = sc.next().toLowerCase();
            
            //take user input for choice of operands and display result
            System.out.println("Please enter your first operand:");
            double a = sc.nextDouble();
            System.out.println("Please enter your second operand:");
            double b = sc.nextDouble();
            
            double result = 0;
            //calculate results
            switch(operation){
                case "add":
                    result = SimpleCalculator.add(a, b);
                    break;
                case "subtract":
                    result = SimpleCalculator.subtract(a, b);
                    break;
                case "multiply":
                    result = SimpleCalculator.multiply(a, b);
                    break;
                case "divide":
                    result = SimpleCalculator.divide(a, b);
                    break;
            }
            System.out.printf("Result = %f\n\n", result);
            
            //keep prompting user to calculate again until they provide a valid input
            while (true){
                System.out.println("Would you like to calculate again? (y/n)");
                char decision = sc.next().charAt(0);
                if (decision == 'y'){
                    break;
                }
                else if (decision == 'n'){
                    System.out.println("Thank you for using the simple calculator.");
                    return;
                }
            }
        }
            
            
            
    }
}
