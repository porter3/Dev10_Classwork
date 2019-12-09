package com.jakeporter.rockpaperscissorsrefactored;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author jake
 */
public class RpsRunner {

    private int roundsToPlay = 0;
    private int playedRounds = 0;
    private int wins = 0;
    private int losses = 0;
    private int ties = 0;
    private String userChoiceString;
    private int userChoiceInt;
    private String compChoiceString;
    private int compChoice;
    
    public void roundsInputValidator(int roundsToPlay){
        if ((roundsToPlay <= 10) && (roundsToPlay >= 1)){
            this.roundsToPlay = roundsToPlay;        
        }
    }
    
    public void generateComputerChoice(){
        Random rndm = new Random();
        this.compChoice =  rndm.nextInt(5);
        switch (this.compChoice) {
                case 0:
                    this.compChoiceString = "rock";
                    break;
                case 1:
                    this.compChoiceString = "paper";
                    break;
                case 2:
                    this.compChoiceString = "scissors";
                    break;
                case 3:
                    this.compChoiceString = "lizard";
                    break;
                default:
                    this.compChoiceString = "spock";
                    break;
            }
    }
    
    public void setUserChoices(String userChoiceString){        
        while (true){
                if (userChoiceString.compareToIgnoreCase("rock") == 0){
                    this.userChoiceString = "rock";
                    this.userChoiceInt = 0;
                    break;
                }
                else if (userChoiceString.compareToIgnoreCase("paper") == 0){
                    this.userChoiceString = "paper";
                    this.userChoiceInt = 1;
                    break;
                }
                else if (userChoiceString.compareToIgnoreCase("scissors") == 0){
                    this.userChoiceString = "scissors";
                    this.userChoiceInt = 2;
                    break;
                }
                else if (userChoiceString.compareToIgnoreCase("lizard") == 0){
                    this.userChoiceString = "lizard";
                    this.userChoiceInt = 3;
                    break;
                }
                else if (userChoiceString.compareToIgnoreCase("spock") == 0){
                    this.userChoiceString = "spock";
                    this.userChoiceInt = 4;
                    break;
                }
                else{
                    System.out.println("Input not recognized. Please enter 'rock', 'paper', 'scissors', 'lizard', or 'spock'.");
                }
            }
    }
    
    public int determineOutcome(int userChoice, int compChoice) {
        int result = 2; // 2 (default) is code for a tie, 0 is win, 1 is lose.
        // rock = 0, paper = 1, scissors = 2, lizard = 3, spock = 4
        switch (userChoice) {
            case 0:
                // rock
                if ((compChoice == 2) || (compChoice == 3)) {
                    result = 0;
                } else if ((compChoice == 1) || (compChoice == 4)) {
                    result = 1;
                }
                break;
            case 1:
                // paper
                if ((compChoice == 0) || (compChoice == 4)) {
                    result = 0;
                } else if ((compChoice == 2) || (compChoice == 3)) {
                    result = 1;
                }
                break;
            case 2:
                // scissors
                if ((compChoice == 1) || (compChoice == 3)) {
                    result = 0;
                } else if ((compChoice == 0) || (compChoice == 4)) {
                    result = 1;
                }
                break;
            case 3:
                // lizard
                if ((compChoice == 1) || (compChoice == 4)) {
                    result = 0;
                } else if ((compChoice == 0) || (compChoice == 2)) {
                    result = 1;
                }
                break;
            case 4:
                // spock
                if ((compChoice == 0) || (compChoice == 2)) {
                    result = 0;
                } else if ((compChoice == 3) || (compChoice == 1)) {
                    result = 1;
                }
                break;
        }
        return result;
    }

    public int getRoundsToPlay() {
        return roundsToPlay;
    }

    public void setRoundsToPlay(int roundsToPlay) {
        this.roundsToPlay = roundsToPlay;
    }

    public String getUserChoiceString() {
        return userChoiceString;
    }

    public void setUserChoiceString(String userChoiceString) {
        this.userChoiceString = userChoiceString;
    }

    public int getUserChoiceInt() {
        return userChoiceInt;
    }

    public void setUserChoiceInt(int userChoiceInt) {
        this.userChoiceInt = userChoiceInt;
    }

    public String getCompChoiceString() {
        return compChoiceString;
    }

    public void setCompChoiceString(String compChoiceString) {
        this.compChoiceString = compChoiceString;
    }

    public int getCompChoice() {
        return compChoice;
    }

    public void setCompChoice(int compChoice) {
        this.compChoice = compChoice;
    }
    
    

}
