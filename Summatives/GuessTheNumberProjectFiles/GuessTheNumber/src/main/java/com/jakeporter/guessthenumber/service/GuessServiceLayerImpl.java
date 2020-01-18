package com.jakeporter.guessthenumber.service;

import com.jakeporter.guessthenumber.data.GuessGameDao;
import com.jakeporter.guessthenumber.data.GuessRoundDao;
import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author jake
 */

@Component
public class GuessServiceLayerImpl implements GuessServiceLayer{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    GuessGameDao gameDao;
    
    @Autowired
    GuessRoundDao roundDao;
    
    public GuessServiceLayerImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int startGame() {
        String gameAnswer = generateAnswer();
        // create a game, populate it with the answer, add it to DB
        Game createdGame = gameDao.addGame(gameAnswer);
        return createdGame.getGameId();
    }

    @Override
    public String generateAnswer() {
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return String.format("%04d", randomInt);
    }

    @Override
    public String calculateGuess(String guess, int gameId) {
        // get game's answer
        final String SELECT_ANSWER = "SELECT answer FROM game WHERE gameID = ?";
        String answer = jdbcTemplate.queryForObject(SELECT_ANSWER, String.class, gameId);
        
        // check guess against answer
        int partialMatch = 0;
        int exactMatch = 0;
            // check each guess digit against each answer digit
            for (int i = 0; i < 4; i++){
                // if exact match, increment completeMatch counter
                if (guess.charAt(i) == answer.charAt(i)){
                    exactMatch++;
                }
                // if partial match, increment partialMatch counter
                else if(answer.contains(String.valueOf(guess.charAt(i)))){
                    partialMatch++;
                } 
            }
        return String.format("e:%d:p:%d", exactMatch, partialMatch);
    }

    @Override
    public boolean checkIfCorrect(String guessInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void markGameWon(int gameId) {
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