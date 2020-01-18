package com.jakeporter.guessthenumber.controllers;

import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.GuessGameIDHolder;
import com.jakeporter.guessthenumber.entities.Round;
import com.jakeporter.guessthenumber.service.GuessServiceLayer;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        System.out.println("HOLDER's GAME ID: " + holder.getGameId());
        String guessInfo = service.calculateGuess(holder.getGuess(), holder.getGameId());
        System.out.println("USER GUESS INFORMATION: " + guessInfo); // PT
        // create a new Round and populate it
        Round round = service.addNewRound(holder.getGuess(), guessInfo, holder.getGameId());
        System.out.println("ROUND ADDED"); // PT
        // check if guessInfo is "e:4:p:0"
        boolean isCorrect = service.checkIfCorrect(guessInfo);
        System.out.println("ANSWER IS CORRECT?: " + Boolean.valueOf(isCorrect).toString().toUpperCase()); // PT
        if (isCorrect){
            service.markGameWon(holder.getGameId());
        }
        return new ResponseEntity(round, HttpStatus.OK);
    }
    
    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAllGames(){
        return new ResponseEntity(service.getAllGames(), HttpStatus.OK);
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId){
        return new ResponseEntity(service.getGameById(gameId), HttpStatus.OK);
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsForGame(@PathVariable int gameId){
        return new ResponseEntity(service.getRoundsForGame(gameId), HttpStatus.OK);
    }
}
