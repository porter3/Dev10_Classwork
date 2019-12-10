package com.jakeporter.shapesandperimeters;

/**
 *
 * @author jake
 */
public class Square extends Shape {

    private double sideLength;
    
    public Square(String color){
        // calls the parent constructor
        super(color);
    }
    
    @Override
    double getArea() {
        return sideLength * sideLength;
    }

    @Override
    double getPerimeter() {
        return sideLength * 4;
    }
}
    