package com.jakeporter.fruitsbasket;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        String[] fruits = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        
        String[] oranges = new String[50];
        String[] apples = new String[50];
       
        int orangesIndex = 0;
        int applesIndex = 0;
        for (int i = 0; i < fruits.length; i++){
            if (fruits[i].compareTo("Orange") == 0){
                oranges[orangesIndex] = "Orange";
                orangesIndex++;
            }
            else{
                apples[applesIndex] = "Apple";
                applesIndex++;
            }
        }
        System.out.printf("Total # of fruits in basket: %d\n", orangesIndex + applesIndex);
        System.out.printf("Number of apples: %d\n", applesIndex);
        System.out.printf("Number of oranges: %d", orangesIndex);
    }
    
}
