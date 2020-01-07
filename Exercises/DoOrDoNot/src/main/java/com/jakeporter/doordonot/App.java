package com.jakeporter.doordonot;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Should I do it?(y/n)");
        boolean doIt = false;
        
        if (sc.next().equals("y")){
            doIt = true;
        }
        
        boolean iDidIt = false;
        
        /* will always evaluated iDidIt to true
        do{
            iDidIt = true;
            break;
        }
        while (doIt);*/
        
        //will only evaluate iDidIt to true if user chooses 'y'
        while (doIt){
            iDidIt = true;
            break;
        }
        
        if (doIt && iDidIt){
            System.out.println("I did it!");
        }
        else if (!doIt && iDidIt) {
            System.out.println("I know you said not to ... but I totally did anyways.");
        }
        else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
}
