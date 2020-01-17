package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Game;
import java.util.List;

/**
 *
 * @author jake
 */
public interface GuessGameDao {

    /**
     * Takes a generated answer, creates a Game populated with answer and other fields,
     * adds it to DB.
     * @param answer
     * @return Game, populated with info from DB
     */
    public Game addGame(String answer);
    
    public List<Game> getAllGames();
    
    public Game getGameById(int gameId);
    
    public void markGameWon(int gameId);
}
