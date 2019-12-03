package com.jakeporter.questfortheuserinput;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class QuestForTheUserInput {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        String yourName;
        String yourQuest;
        double velocityOfSwallow;
        
        System.out.println("What is your name?? ");
        yourName = sc.nextLine();
        
        System.out.println("What is your quest? ");
        yourQuest = sc.nextLine();
        
        System.out.println("What is the airspeed velocity of an unladen swallow?!?! ");
        velocityOfSwallow = sc.nextDouble();
        
        System.out.println(velocityOfSwallow);
        System.out.println(yourName);
        System.out.println(yourQuest);
    }
}
