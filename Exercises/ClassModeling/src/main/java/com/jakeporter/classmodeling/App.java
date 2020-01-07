package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        House jake = new House(8014, "Bristle Ln", "Charlotte", "NC", "08867");
        jake.printAddress();
        
        HouseDesign jakeDesign = new HouseDesign(21, 40, 40, 2, 14, true);
        int jakeSquareFeet = jakeDesign.calculateSquareFootage();
        System.out.println(jakeSquareFeet);
        
        Car mazdaThree2019 = new Car("Mazda", "3", 2019, 0, true, 12);
        mazdaThree2019.sellCar();
        System.out.println(mazdaThree2019.getStock() + " units left");
        
        MarioKart luigiKart = new MarioKart("Luigi's Kart", 8,5,5);
        luigiKart.receiveItem("blue shell");
        luigiKart.useItem();
        
        IceCream1 chocolateAlmond = new IceCream1("chocolate", true);
        System.out.println(chocolateAlmond.getFlavor());
        
        //unfinished, has become busywork
    }
}
