package com.jakeporter.fieldday;

import java.util.Scanner;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a last name:");
        String lastName = sc.next();
        String team = "";
        
        if (lastName.compareTo("Baggins") <= 0){
            team = "Red Dragons";
        }
        else if (lastName.compareTo("Dresden") <= 0){
            team = "Dark Wizards";
        }
        else if (lastName.compareTo("Howl") <= 0){
            team = "Moving Castles";
        }
        else if (lastName.compareTo("Potter") <= 0){
            team = "Golden Snitches";
        }
        else if (lastName.compareTo("Vimes") <= 0){
            team = "Night Guards";
        }
        else{
            team = "Black Holes";
        }
        
        System.out.printf("Aha! You're on the team \"%s\"!\n", team);
        System.out.println("Good luck in the games!");
    }
}
