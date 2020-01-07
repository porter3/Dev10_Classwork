package com.jakeporter.bigdecimalcodealong;

import java.math.BigDecimal;

/**
 *
 * @author jake
 */
public class App {
    
    public static void main(String[] args){
        
        BigDecimalMath myMath = new BigDecimalMath();
        
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("3");
        System.out.println(myMath.calculate(MathOperator.PLUS, a, b));
        System.out.println(myMath.calculate(MathOperator.MINUS, a, b));
        System.out.println(myMath.calculate(MathOperator.MULTIPLY, a, b));
        System.out.println(myMath.calculate(MathOperator.DIVIDE, a, b));
    }

}
