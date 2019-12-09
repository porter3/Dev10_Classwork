package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */
public class Car {
    private String make;
    private String model;
    private int year;
    private int mileage;
    private boolean autoTransmission;
    private int stock;

    public Car(String make, String model, int year, int mileage, boolean autoTransmission, int stock) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.autoTransmission = autoTransmission;
        this.stock = stock;
    }

    public boolean isAutoTransmission() {
        return autoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        this.autoTransmission = autoTransmission;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void sellCar(){
        stock--;
    }

    
}
