package com.jakeporter.allthetrivia;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String args[]){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("1,024 Gigabytes is equal to one what?  ");
        String answerA = sc.nextLine();
        System.out.print("In our Solar System, which is the only planet that rotates clockwise?  ");
        String answerB = sc.nextLine();
        System.out.print("The largest volcano ever discovered in our Solar System is located on which planet?  ");
        String answerC = sc.nextLine();
        System.out.print("What is the most abundant element in the earth's atmosphere?  ");
        String answerD = sc.nextLine();
        
        System.out.printf("\nWow, 1,024 Gigabytes is a %s!\n", answerC);
        System.out.printf("I didn't know that the largest ever volacano was discovered on %s!\n", answerA);
        System.out.printf("That's amazing that %s is the most abundant element in the atmosphere...\n", answerB);
        System.out.printf("%s is the only planet that rotates clockwise, neat!", answerD);
    }
}
