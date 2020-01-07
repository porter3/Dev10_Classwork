package com.jakeporter.coinflipper;

import java.util.Random;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Random flip = new Random();
        String flipResult;
        boolean flipBoolean = flip.nextBoolean();
        
        System.out.println("Ready, set, flip!");
        
        if (flipBoolean == true){
            flipResult = "heads";
        }
        else{
            flipResult = "tails";
        }
        
        System.out.printf("You got %S!", flipResult);
    }
}
