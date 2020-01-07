package com.jakeporter.fruitsalad;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args) {
        
        String[] fruits = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        
        String[] fruitSalad = new String[12];
        String[] berries = new String[20];
        String[] apples = new String[20];
        String[] oranges = new String[20];
        String[] tomatoes = new String[20];
        String[] otherFruit = new String[20];
        
        int berriesCount = 0;
        int applesCount = 0;
        int orangesCount = 0;
        int tomatoesCount = 0;
        int otherCount = 0;
        // sort fruits out into their respective arrays
        for (int i = 0; i < fruits.length; i++){
            if (fruits[i].contains("berry") == true){
                berries[berriesCount] = fruits[i];
                berriesCount++;
            }
            else if (fruits[i].contains("Apple") == true){
                apples[applesCount] = fruits[i];
                applesCount++;
            }
            else if (fruits[i].contains("Orange") == true){
                oranges[orangesCount] = fruits[i];
                orangesCount++;
            }
            else if (fruits[i].contains("Tomato") == false){
                otherFruit[otherCount] = fruits[i];
                otherCount++;
            }
        }
        //add fruits in order of priority to fruitSalad array
        int i = 0;
        //add as many berries as possible
        for (int j = 0; j < berriesCount; j++){
            fruitSalad[i] = berries[j];
            System.out.print(fruitSalad[i] + ", ");
            i++;
        }
        //add three apples
        for (int k = 0; k < 3; k++){
            fruitSalad[i] = apples[k];
            System.out.print(fruitSalad[i] + ", ");
            i++;
        }
        //add 2 oranges
        for (int l = 0; l < 2; l++){
            fruitSalad[i] = oranges[l];
            System.out.print(fruitSalad[i] + ", ");
            i++;
        }
        // add any other fruit to fill up the salad with 12 varieties
        // 'i' is the conditional to stop, 'm' is incremented within loop to allow still allow looping through otherFruit array
        for (int m = 0; i < 12; i++){
            fruitSalad[i] = otherFruit[m];
            System.out.print(fruitSalad[i] + ", ");
            m++;
        }
    }
}
