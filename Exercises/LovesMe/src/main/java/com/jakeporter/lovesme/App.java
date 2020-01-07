package com.jakeporter.lovesme;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        int petals = 34;
        
        //written with a while loop (vs. a do-while) since you don't need to worry about the loop not running at all
        while (petals > 0){
            if (petals % 2 == 0){
                System.out.println("It LOVES me NOT!");
            }
            else{
                System.out.println("It LOVES me!");
            }
            petals--;
        }
    }
}
