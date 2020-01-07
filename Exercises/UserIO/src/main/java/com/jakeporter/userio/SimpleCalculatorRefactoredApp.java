package com.jakeporter.userio;

/**
 *
 * @author jake
 */
public class SimpleCalculatorRefactoredApp {
    public static void main(String[] args){
        
        while (true){
            SimpleCalculator calc = new SimpleCalculator();
            Reader reader = new Reader();
            
            String operation = reader.readString("Would you like to add, subtract, multiply, or divide?");
            //TODO
            //add validation for input String
            
            // read in doubles to operate on
            double doubleA = reader.readDouble("Please enter your first operand:  ");
            double doubleB = reader.readDouble("Please enter your second operand:  ");
            
            double result;
            //determine method to execute
            switch(operation){
                case "add":
                    result = calc.add(doubleA, doubleB);
                    break;
                case "subtract":
                    result = calc.subtract(doubleA, doubleB);
                    break;
                case "multiply":
                    result = calc.multiply(doubleA, doubleB);
                    break;
                case "divide":
                    result = calc.divide(doubleA, doubleB);
                    break;
            }
            reader.print("Calculate again? (y/n)  ");
            //TODO
            //check if user wants to continue or not, break out if they don't
        }
    }
}
