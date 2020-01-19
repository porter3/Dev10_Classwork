/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jake
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessGameDaoTest {
    
    @Autowired
    GuessGameDao gameDao;
    
    @Autowired
    JdbcTemplate jdbc = new JdbcTemplate();
    
    public static final class GameMapper implements RowMapper<Game>{
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setFinishedGame(rs.getBoolean("finishedGame"));
            game.setGameStartTime(rs.getTimestamp("gameStartTime"));
            return game;
        }
    }
    
    public GuessGameDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("SETUP IS RUNNING FOR GAME DAO TEST");
        jdbc.update("DROP TABLE round");
        jdbc.update("DROP TABLE game");
        jdbc.update("CREATE TABLE game(gameID INT PRIMARY KEY AUTO_INCREMENT, answer CHAR(4) NOT NULL, finishedGame BOOLEAN NOT NULL,gameStartTime DATETIME NOT NULL)");
        jdbc.update("CREATE TABLE round(roundID INT PRIMARY KEY AUTO_INCREMENT, gameID INT NOT NULL, userGuess CHAR(4) NOT NULL, guessInfo CHAR(7) NOT NULL, roundTimestamp DATETIME NOT NULL, FOREIGN KEY fk_round_game(gameID) REFERENCES game(gameID))");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GuessGameDao.
     */
    @Test
    public void testAddGetGame() {
        Game game1 = gameDao.addGame("9999");
        assertEquals(1, game1.getGameId());
        
        Game game2 = gameDao.getGameById(1);
        assertEquals(game1, game2);
    }

    /**
     * Test of getAllGames method, of class GuessGameDao.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = gameDao.addGame("1111");
        Game game2 = gameDao.addGame("2222");
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
    }

    /**
     * Test of markGameWon method, of class GuessGameDao.
     */
    @Test
    public void testMarkGameWon() {
        Game game = gameDao.addGame("1234");
        assertEquals(1, game.getGameId()); // make sure game's ID is 1
        assertFalse(game.isFinishedGame()); // make sure game's finishedGame field is false to start
        
        gameDao.markGameWon(game.getGameId());
        game = gameDao.getGameById(1);
        assertTrue(game.isFinishedGame());
        
    }

    /**
     * Test of getGameAnswer method, of class GuessGameDao.
     */
    @Test
    public void testGetGameAnswer() {
        Game game = gameDao.addGame("1234");
        assertEquals(1, game.getGameId());
        
        String gameAnswer = gameDao.getGameAnswer(1); // get the answer from the Game in DB
        assertTrue(gameAnswer.equals(game.getAnswer())); // compare the DB Game's answer to the one in memory
    }
    
}
