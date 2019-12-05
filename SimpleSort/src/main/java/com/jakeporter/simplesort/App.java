package com.jakeporter.simplesort;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};
        
        int[] wholeNumbers = new int[24];
        
        int firstCounter = 0;
        int secondCounter = 0;
        for (int i = 0; i < wholeNumbers.length; i++){
            if (firstHalf[firstCounter] < secondHalf[secondCounter]){
                wholeNumbers[i] = firstHalf[firstCounter];
                firstCounter++;
                //if firstHalf's index is incremented to be out-of-bounds, set its last value to max int value so secondHalf's values will continue to be added.
                if (firstCounter == 12){
                    firstCounter = 11;
                    firstHalf[firstCounter] = Integer.MAX_VALUE;
                }
            }
            else{
                wholeNumbers[i] = secondHalf[secondCounter];
                secondCounter++;
            }
            System.out.printf("%d ", wholeNumbers[i]);
        }
    }
}
