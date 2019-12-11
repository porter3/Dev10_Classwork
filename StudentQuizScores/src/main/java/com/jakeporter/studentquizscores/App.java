package com.jakeporter.studentquizscores;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
                
        StudentDataSet dataset = new StudentDataSet();
        Reader reader = new Reader();
        
        while (true){
            // prompt user to add student, remove student, view list of scores for a student, or view average quiz score for a student
            String action = reader.readString("Please indicate whether you would like to add a student('add'), remove a student('remove'), view list of scores for a student('view list'), view the average quiz score for a student('view averge'), or exit the program('exit'):");
            System.out.println("");
            //ensure consistent casing for user's choice
            action = action.toLowerCase();
            
            switch(action){
                case "add":
                    dataset.addStudent();
                case "remove":
                /*case "view list":
                case "view average":*/
                case "exit":
                    return;
                default:
                    continue;
            }
        }
    }
}
