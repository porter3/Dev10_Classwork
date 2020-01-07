package com.jakeporter.knockknock;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Knock Knock! Guess who!!  ");
        String nameGuess = sc.nextLine();
        
        if(nameGuess.equals("Marty McFly")){
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future.");
        }
        else{
            System.out.printf("Dude, do I -look- like %s?", nameGuess);
        }
    }
}
