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

    private String generateAnswer() {
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return String.format("%04d", randomInt);
    }

    @Override
    public String calculateGuess(String guess, int gameId) {
        // get game's answer
        String answer = gameDao.getGameAnswer(gameId);
        
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
        return guessInfo.equals("e:4:p:0");
    }

    @Override
    public void markGameWon(int gameId) {
        gameDao.markGameWon(gameId);
    }

    @Override
    public Round addNewRound(String guess, String guessInfo, int gameId) {
        return roundDao.addRound(guess, guessInfo, gameId);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames = gameDao.getAllGames();
        hideUnfinishedGameAnswers(allGames);
        return allGames;
    }

    private void hideUnfinishedGameAnswers(List<Game> gamesToFilter) {
        for (int i =0; i < gamesToFilter.size(); i++){
            if (!gamesToFilter.get(i).isFinishedGame()){
                gamesToFilter.get(i).setAnswer("");
            }
        }  
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        // Hide answer for game if it's still in progress
        if (!game.isFinishedGame()){
            game.setAnswer("");
        }
        return game;
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        return roundDao.getRoundsForGame(gameId);
    }
}
