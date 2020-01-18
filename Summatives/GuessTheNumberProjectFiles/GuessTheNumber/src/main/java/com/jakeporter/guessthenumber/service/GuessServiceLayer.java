package com.jakeporter.guessthenumber.service;

import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.util.List;

/**
 *
 * @author jake
 */
public interface GuessServiceLayer {

    public int startGame();
    
    /**
     * Is a helper method for startGame()
     * 
     * Generates a random number between 0 and 9999, converts it to a String with
     * four digits and leading zeroes if need be.
     * @return Answer to populate the Game object
     */
    public String generateAnswer();
    
    /**
     *
     * @param guess
     * @param gameId
     * @return String in the format "e:0:p:0" where 0 is the number of exact
     * and partial matches respectively.
     */
    public String calculateGuess(String guess, int gameId);
    
    /**
     * Checks the String calculated by calculateGuess to determine whether the
     * user's guess is a winning one.
     * @param guessInfo
     * @return 
     */
    public boolean checkIfCorrect(String guessInfo);
    
    /**
     * Updates the DB to mark a game as finished/won.
     * @param gameId
     */
    public void markGameWon(int gameId);
    
    /**
     * Creates a round object, populates the userGuess, guessInfo, and gameId
     * fields, adds it to the DB.
     * @param guess
     * @param guessInfo
     * @param gameId
     * @return Round object populated with info from DB.
     */
    public Round addNewRound(String guess, String guessInfo, int gameId);
    
    public List<Game> getAllGames();
    
    /**
     * Helper method for getAllGames();
     * 
     * @return 
     */
    public void hideUnfinishedGameAnswers(List<Game> gamesToFilter);
    
    public Game getGameById(int gameId);
    
    public List<Round> getRoundsForGame(int gameId);
}
