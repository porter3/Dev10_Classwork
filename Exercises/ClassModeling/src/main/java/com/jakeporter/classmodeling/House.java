package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */
public class House {
    private int houseNumber;
    private String street;
    private String city;
    private String state;
    private String zip;

    public House(int houseNumber, String street, String city, String state, String zip) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        if (state.length() == 2){
        this.state = state;
        }
        if (zip.length() == 5){
            this.zip = zip;
        }
        
        
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state.length() == 2){
        this.state = state;
        }
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if (zip.length() == 5){
        this.zip = zip;
        }
    }
    
    public void printAddress(){
        System.out.println(this.houseNumber + " " + this.street +"\n" + this.city + ", " + this.state + " " + this.zip);
    }
    
    
}
