package com.jakeporter.howmanydaysuntilfriday;

import java.util.Scanner;

/**
 *
 * @author jake
 */

public class HowManyDaysUntilFriday {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter day of the week:");
        String dayStr = sc.next();
        Day day = Day.valueOf(dayStr.toUpperCase());
        System.out.println(day);
         
        switch(day){
            case SUNDAY:
                System.out.println("5 days until Friday");
                break;
            case MONDAY:
                System.out.println("4 days until Friday");
                break;
            case TUESDAY:
                System.out.println("3 days until Friday");
                break;
            case WEDNESDAY:
                System.out.println("2 days until Friday");
                break;
        }
    }
}
