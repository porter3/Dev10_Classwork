package com.jakeporter.guessthenumber.service;

import com.jakeporter.guessthenumber.data.GuessGameDao;
import com.jakeporter.guessthenumber.data.GuessRoundDao;
import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jake
 */
public class GuessServiceLayerImpl implements GuessServiceLayer{

    @Autowired
    GuessGameDao gameDao;
    
    @Autowired
    GuessRoundDao roundDao;

    @Override
    public Game startGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String calculateGuess(String guess, int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkIfCorrect(String guessInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void markGameWon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round addNewRound(String guess, String guessInfo, int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> hideUnfinishedGameAnswers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game getGameById(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
