package com.jakeporter.barelycontrolledchaos;

import java.util.Random;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        String color = randomColor(); // call color method here 
        String animal = randomAnimal(); // call animal method again here 
        String colorAgain = randomColor(); // call color method again here 
        int weight = getRandomIntInRange(5, 200); // call number method, 
            // with a range between 5 - 200 
        int distance = getRandomIntInRange(10, 20); // call number method, 
            // with a range between 10 - 20 
        int number = getRandomIntInRange(10000, 20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = getRandomIntInRange(2, 6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
        
    }
    
    public static String randomColor(){
        Random rndm = new Random();
        int colorChoice = rndm.nextInt(5);
        String color = "";
        switch(colorChoice){
            case 0:
                color = "red";
                break;
            case 1:
                color = "orange";
                break;
            case 2:
                color = "yellow";
                break;
            case 3:
                color = "green";
                break;
            case 4:
                color = "blue";
                break;
        }
        return color;
    }
    
    public static String randomAnimal(){
        Random rndm = new Random();
        int animalChoice = rndm.nextInt(5);
        String animal = "";
        switch(animalChoice){
            case 0:
                animal = "elephant";
                break;
            case 1:
                animal = "giraffe";
                break;
            case 2:
                animal = "monkey";
                break;
            case 3:
                animal = "gorilla";
                break;
            case 4:
                animal = "big bird";
                break;
        }
        return animal;
    }
    
    public static int getRandomIntInRange(int min, int max){
        Random rndm = new Random();
        return rndm.nextInt((max - min) + 1) + min;
    }
}
