package com.jakeporter.shapesandperimeters;

/**
 *
 * @author jake
 */
public class Rectangle extends Shape {

    private int length;
    private int width;
    
    public Rectangle(String color){
        // calls parent constructor
        super(color);
    }
    
    @Override
    double getArea() {
        return length * width;
    }

    @Override
    double getPerimeter() {
        return (length * 2) + (width * 2);
    }

    
}
