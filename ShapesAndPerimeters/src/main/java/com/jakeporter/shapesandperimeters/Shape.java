package com.jakeporter.shapesandperimeters;

/**
 *
 * @author jake
 */
public abstract class Shape {

    protected String color;
    
    abstract double getArea();
    
    abstract double getPerimeter();
    
    //constructor
    public Shape(String color) {
        this.color = color;
    }
    
    void setColor(String color){
        this.color = color;
    }
    
    String getColor(){
        return this.color;
    }
}
    

