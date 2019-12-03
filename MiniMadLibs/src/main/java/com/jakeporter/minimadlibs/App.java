package com.jakeporter.minimadlibs;

import java.util.Scanner;
/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Give me a noun:");
        String nounA = sc.nextLine();
        System.out.println("Give me an adjective:");
        String adjectiveA = sc.nextLine();
        System.out.println("Give me another noun:");
        String nounB = sc.nextLine();
        System.out.println("Give me a number:");
        int numberA = sc.nextInt();
        sc.nextLine();
        System.out.println("Give me another adjective:");
        String adjectiveB = sc.nextLine();
        System.out.println("Give me three plural nouns:");
        String pluralNounA = sc.nextLine();
        String pluralNounB = sc.nextLine();
        String pluralNounC = sc.nextLine();
        System.out.println("Give me a verb in the infinitive form:");
        String verbA = sc.nextLine();
        System.out.println("Give me the past participle of the same verb:");
        String pastParticiple = sc.nextLine();
        
        System.out.printf("%s: the %s frontier. These are the voyages of the starship %s. \n" +
"    Its %d-year mission: to explore strange %s %s, to seek out %s %s and %s %s, \n" +
"    to boldly %s where no one has %s before.", nounA, adjectiveA, nounB, numberA, adjectiveB, 
pluralNounA, adjectiveB, pluralNounB, adjectiveB, pluralNounC, verbA, pastParticiple);
    }
}
