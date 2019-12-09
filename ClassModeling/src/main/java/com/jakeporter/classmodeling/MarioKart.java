package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */

public class MarioKart {
    private String name;
    private int topSpeed;
    private int acceleration;
    private int handling;
    private boolean isBike;
    //does it make sense to initialize a variable up here if it's going to start the same no matter how an object is instantiated?
    private String itemHeld; //set to "" up here?

    //default is for kart to be a car
    public MarioKart(String name, int topSpeed, int acceleration, int handling) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.handling = handling;
        this.isBike = false;
        this.itemHeld = "";
    }

    public MarioKart(String name, int topSpeed, int acceleration, int handling, boolean isBike) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.handling = handling;
        this.isBike = isBike;
        this.itemHeld = "";
    }

    public boolean isIsBike() {
        return isBike;
    }

    public void setIsBike(boolean isBike) {
        this.isBike = isBike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }
    
    public void receiveItem(String item){
        this.itemHeld = item;
    }
    
    public void useItem(){
        System.out.printf("%s used %s!", name, itemHeld);
    }
}
