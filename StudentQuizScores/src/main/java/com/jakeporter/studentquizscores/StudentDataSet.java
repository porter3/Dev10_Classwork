package com.jakeporter.studentquizscores;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jake
 */
public class StudentDataSet {
    
    private HashMap<String, ArrayList<Integer>> studentData;
    
    public void viewStudents(){
        
    }
    
    public void addStudent(){
        Reader reader = new Reader();
        HashMap<String, ArrayList<Integer>> studentData = new HashMap();
        String student = reader.readString("Please enter student name:  ");
        //prompt user for entering scores separated w/ commas
        String scoreString = reader.readString("Please enter student scores separated by commas(no whitespace):  ");
    //parse scoreString to turn scores into ArrayList of integers
        //convert scoreString into String Arraylist
        String[] stringList = scoreString.split(",");
        ArrayList<Integer> scores = new ArrayList<>();
        //loop over string array, converting each value to integer and assigning it to Integer ArrayList
        for (String score : stringList){
            scores.add(Integer.parseInt(score));
        }   
        
        System.out.println(scores);         // test statement
        
        //add student and ArrayList to HashMap as a key-value pair
        studentData.put(student, scores); 
        this.studentData = studentData;
        System.out.println(this.studentData);   // test statement
    }
    
    public void removeStudent(){
        
    }
    
    public void viewScores(){
        
    }
    
    public void viewAverageScore(){
        
    }
}
