package com.jakeporter.rockpaperscissorsrefactored;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        RpsRunner runner = new RpsRunner();
        Scanner sc = new Scanner(System.in);
        
        //repeat game until user chooses to not continue
        while (true){
            
            //get user input for rounds to play and validate
            System.out.println("How many rounds would you like to play?");
            runner.roundsInputValidator(sc.nextInt());
            if (runner.getRoundsToPlay() == 0){
                System.out.println("Round number was not between 1 and 10. Please restart the program and try again.");
                return;
            }
            
            while ()
            
            
        }
    }
}
