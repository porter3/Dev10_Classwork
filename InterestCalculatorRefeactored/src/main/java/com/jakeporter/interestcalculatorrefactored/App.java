package com.jakeporter.interestcalculatorrefactored;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        // get input and set variables
        InterestCalculator calculator = new InterestCalculator();
        
        // set variables
        calculator.setVariables();
        
        // calculate and print results
        calculator.calculateAndPrintResults();
        
    }
}
