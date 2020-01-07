package com.jakeporter.doggenetics;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Random rndm = new Random();
        // get user input for dog name
        System.out.println("What is your dog's name?");
        String dogName = sc.nextLine();
        
        String[] dogBreeds = {"Golden Retriever", "Poodle", "Chihuahua", "Jack Russell", "Pug"};
        
        dogBreeds = shuffleArray(dogBreeds);  //shuffle array to avoid heavy percentage distribution for first breed in array every time
        for (String breed:dogBreeds){               //test statement
            System.out.println(breed);
        }
        
        //create percentage counter
        int percentageUsed = 0;
        
        int goldenRetriever = 0;
        int poodle = 0;
        int chihuahua = 0;
        int jackRussell = 0;
        int pug = 0;
        
        //assign random percentage to dog breed
        for (int i = 0; i < dogBreeds.length; i++){
            //create random number between 1 and remaining percentage
            int randomPercentage = rndm.nextInt((100 - percentageUsed) + 1);
            //ensure last percentage is used
            if (i == 4){
                randomPercentage = 100 - percentageUsed;
            }
            
            //assign percentage to breed
            switch(dogBreeds[i]){
                case "Golden Retriver":
                    goldenRetriever = randomPercentage;
                    break;
                case "Poodle":
                    poodle = randomPercentage;
                    break;
                case "Chihuahua":
                    chihuahua = randomPercentage;
                    break;
                case "Jack Russell":
                    jackRussell = randomPercentage;
                    break;
                case "Pug":
                    pug = randomPercentage;
                    break;
            }
            percentageUsed += randomPercentage;
        }
        System.out.printf("%s is:\n\n%d Golden Retriever\n%d Poodle\n%d Chihuahua\n%d Jack Russell\n%d Pug", dogName, goldenRetriever, poodle, chihuahua, jackRussell, pug);
        
    }
    
    
    
    
    
    
    
    
    public static String[] shuffleArray(String[] array){
        Random rndm = new Random();		
		for (int i = 0; i < array.length; i++) {
		    int randomIndex = rndm.nextInt(array.length);
		    String temp = array[i];
		    array[i] = array[randomIndex];
		    array[randomIndex] = temp;
		}
		return array;
    }
    
        
}
