package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */
public class HouseDesign {
    private int heightInFeet;
    private int lengthInFeet;
    private int widthInFeet;
    private int floors;
    private int numOfWindows;
    private boolean hasCentralAC;

    public HouseDesign(int heightInFeet, int lengthInFeet, int widthInFeet, int floors, int numOfWindows, boolean hasCentralAC) {
        this.heightInFeet = heightInFeet;
        this.lengthInFeet = lengthInFeet;
        this.widthInFeet = widthInFeet;
        this.floors = floors;
        this.numOfWindows = numOfWindows;
        this.hasCentralAC = hasCentralAC;
    }

    public boolean isHasCentralAC() {
        return hasCentralAC;
    }

    public void setHasCentralAC(boolean hasCentralAC) {
        this.hasCentralAC = hasCentralAC;
    }

    public int getHeightInFeet() {
        return heightInFeet;
    }

    public void setHeightInFeet(int heightInFeet) {
        this.heightInFeet = heightInFeet;
    }

    public int getLengthInFeet() {
        return lengthInFeet;
    }

    public void setLengthInFeet(int lengthInFeet) {
        this.lengthInFeet = lengthInFeet;
    }

    public int getWidthInFeet() {
        return widthInFeet;
    }

    public void setWidthInFeet(int widthInFeet) {
        this.widthInFeet = widthInFeet;
    }
    
    public int getFloors() {
    return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }
    

    public int getNumOfWindows() {
        return numOfWindows;
    }

    public void setNumOfWindows(int numOfWindows) {
        this.numOfWindows = numOfWindows;
    }
    
    public void installAC(){
        if (hasCentralAC == false){
            hasCentralAC = true;
        }
    }

    public int calculateSquareFootage(){
        return widthInFeet * heightInFeet * floors;
    }
    
    
}
