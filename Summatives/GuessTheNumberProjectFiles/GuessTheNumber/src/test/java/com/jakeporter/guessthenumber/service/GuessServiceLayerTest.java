/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.guessthenumber.service;

import com.jakeporter.guessthenumber.data.GuessGameDao;
import com.jakeporter.guessthenumber.data.GuessRoundDao;
import com.jakeporter.guessthenumber.data.GuessRoundDaoDBImpl.RoundMapper;
import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessServiceLayerTest {
    
    @Autowired
    GuessServiceLayer service;
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    GuessGameDao gameDao;
    
    @Autowired
    GuessRoundDao roundDao;
    
    public GuessServiceLayerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
            System.out.println("SETUP IS RUNNING FOR SERVICE LAYER TEST");
        jdbc.update("DROP TABLE round");
        jdbc.update("DROP TABLE game");
        jdbc.update("CREATE TABLE game(gameID INT PRIMARY KEY AUTO_INCREMENT, answer CHAR(4) NOT NULL, finishedGame BOOLEAN NOT NULL,gameStartTime DATETIME NOT NULL)");
        jdbc.update("CREATE TABLE round(roundID INT PRIMARY KEY AUTO_INCREMENT, gameID INT NOT NULL, userGuess CHAR(4) NOT NULL, guessInfo CHAR(7) NOT NULL, roundTimestamp DATETIME NOT NULL, FOREIGN KEY fk_round_game(gameID) REFERENCES game(gameID))");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of startGame method, of class GuessServiceLayer.
     */
    @Test
    public void testStartGame() {
        int i = service.startGame();
        assertEquals(1, i); // assert a game was created with an ID of 1
    }

    /**
     * Test of calculateGuess method, of class GuessServiceLayer.
     */
    @Test
    public void testCalculateGuess() {
        
        Game game = new Game();
        gameDao.addGame("1234"); // add a game to the DB with an answer of "1234"
        
        // pass in an answer and the ID of the only Game in the GameDao
        String guessInfo = service.calculateGuess("0000", 1);
        assertTrue(guessInfo.equals("e:0:p:0"));
        
        guessInfo = service.calculateGuess("1567", 1);
        assertTrue(guessInfo.equals("e:1:p:0"));
        
        guessInfo = service.calculateGuess("5671", 1);
        assertTrue(guessInfo.equals("e:0:p:1"));
        
        guessInfo = service.calculateGuess("1235", 1);
        assertTrue(guessInfo.equals("e:3:p:0"));
        
        guessInfo = service.calculateGuess("1234", 1);
        assertTrue(guessInfo.equals("e:4:p:0"));
        
        guessInfo = service.calculateGuess("4321", 1);
        assertTrue(guessInfo.equals("e:0:p:4"));
    }

    /**
     * Test of checkIfCorrect method, of class GuessServiceLayer.
     */
    @Test
    public void testCheckIfCorrect() {
        assertTrue(service.checkIfCorrect("e:4:p:0"));
        assertFalse(service.checkIfCorrect("e:0:p:0"));
        assertFalse(service.checkIfCorrect("e:0:p:4"));
        assertFalse(service.checkIfCorrect("e:4:p:4"));
        assertFalse(service.checkIfCorrect("e:2:p:2"));
    }

    /**
     * Test of markGameWon method, of class GuessServiceLayer.
     */
    @Test
    public void testMarkGameWon() {
        int gameID = service.startGame();
        assertFalse(service.getGameById(gameID).isFinishedGame());
        
        service.markGameWon(gameID);
        assertTrue(service.getGameById(gameID).isFinishedGame());
    }

    /**
     * Test of addNewRound method, of class GuessServiceLayer.
     */
    @Test
    public void testAddNewRound() {
        int i = service.startGame();
        assertEquals(1, i); // ensure game created has ID of 1
        
        Round round = service.addNewRound("1111", "e:0:p:0", 1); // guessInfo doesn't matter here
        String SELECT_ROUND = "SELECT * FROM round WHERE roundID = 1";
        Round roundFromDB = jdbc.queryForObject(SELECT_ROUND, new RoundMapper());
        assertEquals(1, roundFromDB.getRoundId()); // asserts that the Round in DB exists and has an ID of 1
    }

    /**
     * Test of getAllGames method, of class GuessServiceLayer.
     */
    @Test
    public void testGetAllGames() {
        int gameOneID = service.startGame();
        int gameTwoID = service.startGame();
        assertEquals(1, gameOneID);
        assertEquals(2, gameTwoID);
        
        Game gameOne = service.getGameById(1);
        Game gameTwo = service.getGameById(2);
        List<Game> games = service.getAllGames();
        
        assertTrue(games.contains(gameOne));
        assertTrue(games.contains(gameTwo));
        assertEquals(2, games.size());
    }

    /**
     * Test of getGameById method, of class GuessServiceLayer.
     */
    @Test
    public void testGetGameById() {
        int gameOneID = service.startGame();
        
        Game gameOne = service.getGameById(gameOneID);
        assertEquals(gameOneID, gameOne.getGameId());
    }

    /**
     * Test of getRoundsForGame method, of class GuessServiceLayer.
     */
    @Test
    public void testGetRoundsForGame() {
        int gameOne = service.startGame();
        int gameTwo = service.startGame();
        assertEquals(1, gameOne);
        assertEquals(2, gameTwo);
        
        Round roundA = service.addNewRound("0000", "e:0:p:0", gameOne);
        System.out.println("ROUND A: " + roundA.toString());
        
        Round roundB = service.addNewRound("0000", "e:0:p:0", gameOne);
        
        Round roundC = service.addNewRound("0000", "e:0:p:0", gameTwo);
        
        List<Round> roundsForGameOne = service.getRoundsForGame(gameOne);
        List<Round> roundsForGameTwo = service.getRoundsForGame(gameTwo);
        
        assertEquals(2, roundsForGameOne.size()); // assert gameOne has two rounds
        assertEquals(1, roundsForGameTwo.size()); // assert gameTwo has one round
        assertTrue(roundsForGameOne.contains(roundA)); // gameOne has roundA
        assertTrue(roundsForGameOne.contains(roundB)); // gameOne has roundB
        assertFalse(roundsForGameOne.contains(roundC)); // gameOne does not have roundC
        assertTrue(roundsForGameTwo.contains(roundC)); // gameTwo has roundC
    }
    
}
