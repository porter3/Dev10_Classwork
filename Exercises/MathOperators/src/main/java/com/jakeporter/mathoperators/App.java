package com.jakeporter.mathoperators;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        MathOperators add = MathOperators.PLUS;
        int a = operate(add, 5,5);
    }
    public static int operate(MathOperators operator, int a, int b){
        int c = 0;
            switch(operator){
                case PLUS:
                    c = a + b;
                case MINUS:
                    c = a - b;
                case MULTIPLY:
                    c = a * b;
                case DIVIDE:
                    c = a / b;
            }
        return c;
    }
}
