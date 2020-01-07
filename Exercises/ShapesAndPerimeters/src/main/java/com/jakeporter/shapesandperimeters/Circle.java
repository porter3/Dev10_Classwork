package com.jakeporter.shapesandperimeters;

/**
 *
 * @author jake
 */
public class Circle extends Shape {

    private double radius;
    private final double pi = 3.14159;
    
    public Circle(String color){
        //calls the parent constructor
        super(color);
    }
    
    @Override
    double getArea() {
        return pi * Math.pow(radius, 2);
    }

    @Override
    double getPerimeter() {
        return 2 * pi * radius;
    }

    
}
