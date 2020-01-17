package com.jakeporter.guessthenumber.controllers;

import com.jakeporter.guessthenumber.data.GuessGameDao;
import com.jakeporter.guessthenumber.data.GuessRoundDao;
import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import com.jakeporter.guessthenumber.service.GuessServiceLayer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jake
 */

@RestController
@RequestMapping("/guessgame/api")
public class GuessTheNumberController {
    
    @Autowired
    GuessServiceLayer service;

    
//    public ResponseEntity<Integer> beginGame(){
//        
//    }
//    
//    public ResponseEntity<Round> guessNumber(String guess, int gameId){
//    
//    }
//    
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
