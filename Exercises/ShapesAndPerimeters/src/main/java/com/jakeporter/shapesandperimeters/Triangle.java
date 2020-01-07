package com.jakeporter.shapesandperimeters;

/**
 *
 * @author jake
 */
public class Triangle extends Shape {

    private double base;
    private double height;
    private double sideA;
    private double sideB;
    private double sideC;
    
    public Triangle(String color){
        // calls the parent constructor
        super(color);
    }

    @Override
    double getArea() {
        return (base * height) / 2;
    }

    @Override
    double getPerimeter() {
        return sideA + sideB + sideC;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setSides(double sideA, double sideB, double sideC){
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }
    
    
}
