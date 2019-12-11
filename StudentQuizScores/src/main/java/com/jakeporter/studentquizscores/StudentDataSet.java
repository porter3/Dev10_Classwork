package com.jakeporter.studentquizscores;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

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
        studentData.put(student.toLowerCase(), scores); 
        this.studentData = studentData;
        System.out.println(this.studentData);   // test statement
    }
    
    public void removeStudent(){
        Reader reader = new Reader();
        // prompt user for student they want to remove
        String student = reader.readString("Please enter the name of the student you would like to remove:  ");
        // check if student exists (student must be ensured to be all lowercase)
        student = student.toLowerCase();
        if (studentData.containsKey(student)){
            studentData.remove(student);
            reader.print("Student removed.");
        }
        else{
            reader.print("Student does not exist.");
        }
        
    }
    
    public void viewScores(){
        Reader reader = new Reader();
        //prompt user to enter the student whose scores they want to view
        String student = reader.readString("Please enter the name of the student whose scores you would like to view:  ");
        
        //print out student's name and scores
        System.out.printf("Student: %s\nQuiz Scores:\n", student);
        Iterator iterator = studentData.get(student).iterator();
        while (iterator.hasNext()){
            System.out.printf("%d  ", iterator.next());
        }
        System.out.println("\n");
    }
    
    public void viewAverageScore(){
        Reader reader = new Reader();
        // prompt user for student to check
        String student = reader.readString("Please enter the name of the student whose average score you would like to view:  ");
        // iterate over scores, and calculate average
        Iterator<Integer> iterator = this.studentData.get(student).iterator();
        
        int quizSum = 0;
        int numberOfScores = 0;
        while (iterator.hasNext()){
            quizSum += iterator.next();
            numberOfScores++;
        }
        
        // print results
        System.out.printf("%s's Average Score: %d", student, quizSum/numberOfScores);
    }
}
