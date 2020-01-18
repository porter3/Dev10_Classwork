package com.jakeporter.guessthenumber.controllers;

import com.jakeporter.guessthenumber.entities.GuessGameIDHolder;
import com.jakeporter.guessthenumber.entities.Round;
import com.jakeporter.guessthenumber.service.GuessServiceLayer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jake
 */

@RestController
@RequestMapping("/guessgame/api")
public class GuessTheNumberController {
    
    @Autowired
    GuessServiceLayer service;

    @PostMapping("/begin")
    public ResponseEntity<Integer> beginGame(){
        int newGameId = service.startGame();
        return new ResponseEntity(newGameId, HttpStatus.CREATED);
    }
    
    @PostMapping("/guess")
    public ResponseEntity<Round> guessNumber(@RequestBody GuessGameIDHolder holder){
        // calculate the correctness of the user's guess
        String guessInfo = service.calculateGuess(holder.getGuess(), holder.getGameId());
        System.out.println(guessInfo);
        // create a new Round and populate it
        Round round = service.addNewRound(holder.getGuess(), guessInfo, holder.getGameId());
        // check if guessInfo is "e:4:p:0"
        boolean isCorrect = service.checkIfCorrect(guessInfo);
        if (isCorrect){
            service.markGameWon(holder.getGameId());
        }
        return new ResponseEntity(round, HttpStatus.OK);
    }
    
//    public ResponseEntity<List<Game>> getAllGames(){
//        
//    }
//    
//    public ResponseEntity<Game> getGameById(int gameId){
//        
//    }
//    
//    public ResponseEntity<List<Round>> getRoundsForGame(int gameId){
//        
//    }
}
