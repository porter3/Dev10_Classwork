package com.jakeporter.bigdecimalcodealong;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author jake
 */
public class BigDecimalMath {

    public BigDecimal calculate(MathOperator operator, BigDecimal a, BigDecimal b){
        switch (operator){
            case PLUS:
                return a.add(b);
            case MINUS:
                return a.subtract(b);
            case MULTIPLY:
                return a.multiply(b);
            case DIVIDE:
                return a.divide(b, 2, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedOperationException("Unknown math operator");
        }
    }
}
