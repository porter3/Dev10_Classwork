package com.jakeporter.guessthenumber.data;

import com.jakeporter.guessthenumber.entities.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jake
 */

@Repository
@Profile("database")
public class GuessRoundDaoDBImpl implements GuessRoundDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GuessRoundDaoDBImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final class RoundMapper implements RowMapper<Round>{
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundID"));
            round.setUserGuess(rs.getString("userGuess"));
            round.setGuessInfo(rs.getString("guessInfo"));
            round.setRoundTimestamp(rs.getTimestamp("roundTimestamp"));
            return round;
        }
    }

    @Override
    @Transactional
    public Round addRound(String guess, String guessInfo, int gameId) {
        Round newRound = new Round();
        Timestamp timeRoundCreated = Timestamp.valueOf(LocalDateTime.now().withNano(0));
        
        final String INSERT_ROUND = "INSERT INTO round (gameId, userGuess, guessInfo, roundTimestamp) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_ROUND, gameId, guess, guessInfo, timeRoundCreated);
        
        // get ID from round created in DB
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        newRound.setRoundId(newId);
        newRound.setUserGuess(guess);
        newRound.setGuessInfo(guessInfo);
        newRound.setRoundTimestamp(timeRoundCreated);
        
        return newRound;
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round WHERE gameID = ?";
        return jdbcTemplate.query(SELECT_ALL_ROUNDS, new RoundMapper(), gameId);
    }
}
