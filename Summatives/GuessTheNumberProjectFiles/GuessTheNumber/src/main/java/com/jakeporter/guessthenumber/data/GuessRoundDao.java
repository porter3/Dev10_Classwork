package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Round;
import java.util.List;

/**
 *
 * @author jake
 */
public interface GuessRoundDao {

    /**
     * Takes a user's guess, what game response they received about that guess,
     * and corresponding gameId- adds them to a created Guess object, writes Guess
     * object to DB.
     * @param guess
     * @param guessInfo
     * @param gameId
     * @return Round, populated with info from DB
     */
    public Round addRound(String guess, String guessInfo, int gameId);
    
    /**
     * Takes a gameId, returns all Rounds for the game.
     * @param gameId
     * @return List of Rounds for a Game
     */
    public List<Round> getRoundsForGame(int gameId);
}
