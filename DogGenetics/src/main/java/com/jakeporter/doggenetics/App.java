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
        
        String[] dogBreeds = {"goldenRetriever", "poodle", "chihuahua", "jackRussell", "pug"};
        int[] dogBreedInts = new int[dogBreeds.length];
        for (int i = 0; i < dogBreeds.length; i++){ // create list of ints for number of dog breeds
            dogBreedInts[i] = i;
        }
        
        int[] dogBreedIntsShuffled = shuffleArray(dogBreedInts);
        for (int breed:dogBreedIntsShuffled){
            System.out.println(breed);
        }
    }
    
    public static int[] shuffleArray(int[] array){
        Random rndm = new Random();		
		for (int i = 0; i < array.length; i++) {
		    int randomIndex = rndm.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomIndex];
		    array[randomIndex] = temp;
		}
		return array;
    }
    
        
}
