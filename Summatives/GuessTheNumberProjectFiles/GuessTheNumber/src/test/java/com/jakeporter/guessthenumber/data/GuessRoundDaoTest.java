/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@RunWith(SpringRunner.class) // try to figure out what SpringRunner is
@SpringBootTest
public class GuessRoundDaoTest {
    
    @Autowired
    GuessRoundDao roundDao;
    
    @Autowired
    GuessGameDao gameDao;
    
    @Autowired
    JdbcTemplate jdbc = new JdbcTemplate();
    
    public static final class RoundMapper implements RowMapper<Round>{
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundID"));
            round.setGameId(rs.getInt("gameID"));
            round.setUserGuess(rs.getString("userGuess"));
            round.setGuessInfo(rs.getString("guessInfo"));
            round.setRoundTimestamp(rs.getTimestamp("roundTimestamp"));
            return round;
        }
    }
    public GuessRoundDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("SETUP IS RUNNING FOR ROUND DAO TEST");
        jdbc.update("DROP TABLE round");
        jdbc.update("DROP TABLE game");
        jdbc.update("CREATE TABLE game(gameID INT PRIMARY KEY AUTO_INCREMENT, answer CHAR(4) NOT NULL, finishedGame BOOLEAN NOT NULL,gameStartTime DATETIME NOT NULL)");
        jdbc.update("CREATE TABLE round(roundID INT PRIMARY KEY AUTO_INCREMENT, gameID INT NOT NULL, userGuess CHAR(4) NOT NULL, guessInfo CHAR(7) NOT NULL, roundTimestamp DATETIME NOT NULL, FOREIGN KEY fk_round_game(gameID) REFERENCES game(gameID))");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class GuessRoundDao.
     */
    @Test
    public void testAddRound() {
        // add game, round, verify round with gameID exists
        Game game = gameDao.addGame("0495");
        // ensure new game's ID is 1
        assertEquals(1, game.getGameId());
        
        Round round = roundDao.addRound("0495", "e:4:p:0", 1);
        
        // get Round back from DB
        Round roundFromDao = jdbc.queryForObject("SELECT * FROM round WHERE roundID = 1", new RoundMapper());
//        System.out.println("IDs: " + round.getRoundId() + ", " + roundFromDao.getRoundId());
//        System.out.println("GUESSES: " + round.getUserGuess() + ", " + roundFromDao.getUserGuess());
//        System.out.println("GUESS INFO: " + round.getGuessInfo() + ", " + roundFromDao.getGuessInfo());
//        System.out.println("GAME IDs: " + round.getGameId() + ", " + roundFromDao.getGameId());
//        System.out.println("TIMESTAMPS: " + round.getRoundTimestamp() + ", " + roundFromDao.getRoundTimestamp());
        assertEquals(round, roundFromDao);
    }

    /**
     * Test of getRoundsForGame method, of class GuessRoundDao.
     */
    @Test
    public void testGetRoundsForGame() {
        Game game1 = gameDao.addGame("5678");
        Game game2 = gameDao.addGame("0000");
        assertEquals(1, game1.getGameId());
        
        Round round1 = roundDao.addRound("9999", "e:0:p:0", 1);
        Round round2 = roundDao.addRound("1111", "e:0:p:0", 1);
        Round round3 = roundDao.addRound("2222", "e:0:p:0", 2);
        
        // assert Game with GameID of 1 has two rounds
        assertEquals(2, roundDao.getRoundsForGame(1).size());
    }
    
}
