package com.jakeporter.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Random rpsGenerator = new Random();
        
        //repeat game until user chooses not to continue
        while (true){
            System.out.println("How many rounds would you like to play?");
            int totalRounds = sc.nextInt();

            //determine if round input is valid
            if (!(totalRounds <= 10) || !(totalRounds >= 1)){
                System.out.println("Round number was not between 1 and 10. Please restart the program and try again.");
                return;
            }

            int playedRounds = 0;
            int wins = 0;
            int losses = 0;
            int ties = 0;
            while (playedRounds < totalRounds){

                // generate computer's RPS choice (0 == rock, 1 == paper, 2 == scissors, 3 == lizard, 4 ==  spock)
                int compChoice = rpsGenerator.nextInt(5);

                int userChoice;
                String userChoiceStr;
                String compChoiceStr;

                // prompt user for RPS choice while 
                while (true){
                    System.out.println("Please enter your choice of rock/paper/scissors/lizard/spock:");
                    String userChoiceStrTemp = sc.next();
                    if (userChoiceStrTemp.compareToIgnoreCase("rock") == 0){
                        userChoice = 0;
                        userChoiceStr = userChoiceStrTemp;
                        break;
                    }
                    else if (userChoiceStrTemp.compareToIgnoreCase("paper") == 0){
                        userChoice = 1;
                        userChoiceStr = userChoiceStrTemp;
                        break;
                    }
                    else if (userChoiceStrTemp.compareToIgnoreCase("scissors") == 0){
                        userChoice = 2;
                        userChoiceStr = userChoiceStrTemp;
                        break;
                    }
                    else if (userChoiceStrTemp.compareToIgnoreCase("lizard") == 0){
                        userChoice = 3;
                        userChoiceStr = userChoiceStrTemp;
                        break;
                    }
                    else if (userChoiceStrTemp.compareToIgnoreCase("spock") == 0){
                        userChoice = 4;
                        userChoiceStr = userChoiceStrTemp;
                        break;
                    }
                    else{
                        System.out.println("Input not recognized. Please enter 'rock', 'paper', 'scissors', 'lizard', or 'spock'.");
                    }
                }

                //convert user's and computer's choices to lowercase strings
                userChoiceStr = userChoiceStr.toLowerCase();
                switch (compChoice) {
                    case 0:
                        compChoiceStr = "rock";
                        break;
                    case 1:
                        compChoiceStr = "paper";
                        break;
                    case 2:
                        compChoiceStr = "scissors";
                        break;
                    case 3:
                        compChoiceStr = "lizard";
                        break;
                    default:
                        compChoiceStr = "spock";
                        break;
                }
                
                //determine outcome of round
                int result = determineRpsOutcome(userChoice, compChoice);

                //increment respective counters and print respective messages
                switch (result) {
                    case 0:
                        System.out.printf("The computer chose %s, you chose %s. You win!\n\n", compChoiceStr, userChoiceStr);
                        wins++;
                        break;
                    case 1:
                        System.out.printf("The computer chose %s, you chose %s. You lose.\n\n", compChoiceStr, userChoiceStr);
                        losses++;
                        break;
                    default:
                        System.out.printf("The computer chose %s, you chose %s. It's a tie.\n\n", compChoiceStr, userChoiceStr);
                        ties++;
                        break;
                }
                playedRounds++;
            }
            System.out.printf("\nResult of the last %d rounds:\n", totalRounds);
            System.out.printf("Wins: %d\nLosses: %d\nTies: %d\n\n", wins, losses, ties);
            
            System.out.println("Would you like to play again? (y/n)");
            while (true){
                String playAgain = sc.next();
                if (playAgain.equalsIgnoreCase("y")){
                    break;
                }
                else if (playAgain.equalsIgnoreCase("n")){
                    return;
                }
            }
        }
    }
    
    public static int determineRpsOutcome(int userChoice, int compChoice) {
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
    
}
