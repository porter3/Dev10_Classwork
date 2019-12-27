package com.jakeporter.classroster.ui;

import java.util.Scanner;

/**
 *
 * @author jake
 */

// this is the console-specific implementation for the UI
public class UserIOConsoleImpl implements UserIO{

    @Override
    public void print(String message){
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt){
        Scanner sc = new Scanner(System.in);
        //display prompt to user
        System.out.println(prompt);
        return sc.nextDouble(); 
    }
    
    @Override
    public double readDouble(String prompt, double min, double max){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(prompt);
            double input = sc.nextDouble();
            if ((input >= min) && (input <= max)){
                return input;
            }
        }
    }
    
    @Override
    public float readFloat(String prompt){
        Scanner sc = new Scanner(System.in);
            System.out.println(prompt);
            return sc.nextFloat();
    }
    
    @Override
    public float readFloat(String prompt, float min, float max){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(prompt);
            float input = sc.nextFloat();
            if ((input <= max) && (input >= min)){
                return input;
            }
        }
    }
    
    @Override
    public int readInt(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextInt();
    }
    
    @Override
    public int readInt(String prompt, int min, int max){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(prompt);
            int input = sc.nextInt();
            if ((input <= max) && (input >= min)){
                return input;
            }
        }
    }
    
    @Override
    public long readLong(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLong();
    }
    
    @Override
    public long readLong(String prompt, long min, long max){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(prompt);   
            long input = sc.nextLong();
            if ((input <= max) && (input >= min)){
                return input;
            }
        }
    }
    
    @Override
    public String readString(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }
}
