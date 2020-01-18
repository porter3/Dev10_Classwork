package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Game;
import com.jakeporter.guessthenumber.entities.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jake
 */

@Repository
@Profile("database")
public class GuessGameDaoDBImpl implements GuessGameDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    public GuessGameDaoDBImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
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

    @Override
    public Game addGame(String answer) {
        Game newGame = createGame(answer);
        final String INSERT_GAME = "INSERT INTO game(answer, finishedGame, gameStartTime) VALUES (?,?,?)";
        jdbcTemplate.update(INSERT_GAME, newGame.getAnswer(), newGame.isFinishedGame(), newGame.getGameStartTime()); // getGameStartTime returns a Timestamp
        System.out.println("GAME ID = " + jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        newGame.setGameId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class)); // get the last insert ID and assign to the Game object
        return newGame;
    }
    
    private Game createGame(String answer){
        Game newGame = new Game();
        newGame.setAnswer(answer);
        newGame.setFinishedGame(false);
        newGame.setGameStartTime(Timestamp.valueOf(LocalDateTime.now().withNano(0)));
        return newGame;
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM GAME";
        return jdbcTemplate.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void markGameWon(int gameId) {
        final String UPDATE_GAME = "UPDATE game SET finishedGame = true WHERE gameID = ?";
        jdbcTemplate.update(UPDATE_GAME, gameId);
    }

}
