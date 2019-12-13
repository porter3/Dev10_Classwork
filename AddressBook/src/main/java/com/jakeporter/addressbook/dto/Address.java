package com.jakeporter.addressbook.dto;

/**
 *
 * @author jake
 */
public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address:\n" + street + "\n" + city + ", " + state + " " + zip;
    }
    
    public String toString(boolean noAddressTag){
        return street + "\n" + city + ", " + state + " " + zip;
    }
    
    public String toString (String addressField){
        String field = "";
        switch(addressField){
            case "street":
                field = street;
                break;
            case "city":
                field = city;
                break;
            case "state":
                field = state;
                break;
            case "zip":
                field = zip;
                break;
        }
        return field;
    }
    
    
}
