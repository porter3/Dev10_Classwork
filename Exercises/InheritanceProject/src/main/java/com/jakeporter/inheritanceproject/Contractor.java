package com.jakeporter.inheritanceproject;

/**
 *
 * @author jake
 */
public class Contractor extends Person {

    private final boolean permanent;
    private double hourlyRate;
    
    public Contractor(String name, int age, Address address, boolean permanent, double hourlyRate){
        super(name, age, address);
        this.permanent = permanent;
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    public String toString(){
        return super.toString() + " Permanent status: " + permanent + "Hourly Rate: " + hourlyRate;
    }
}
