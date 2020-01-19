package com.jakeporter.guessthenumber.entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author jake
 */
public class Round {
    
    private int roundId;
    private int gameId;
    private String userGuess;
    private String guessInfo;
    private Timestamp roundTimestamp;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }
    
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(String userGuess) {
        this.userGuess = userGuess;
    }

    public String getGuessInfo() {
        return guessInfo;
    }

    public void setGuessInfo(String guessInfo) {
        this.guessInfo = guessInfo;
    }

    public Timestamp getRoundTimestamp() {
        return roundTimestamp;
    }

    public void setRoundTimestamp(Timestamp roundTimestamp) {
        this.roundTimestamp = roundTimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.roundId;
        hash = 71 * hash + this.gameId;
        hash = 71 * hash + Objects.hashCode(this.userGuess);
        hash = 71 * hash + Objects.hashCode(this.guessInfo);
        hash = 71 * hash + Objects.hashCode(this.roundTimestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.userGuess, other.userGuess)) {
            return false;
        }
        if (!Objects.equals(this.guessInfo, other.guessInfo)) {
            return false;
        }
        if (!Objects.equals(this.roundTimestamp, other.roundTimestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", userGuess=" + userGuess + ", guessInfo=" + guessInfo + ", roundTimestamp=" + roundTimestamp + '}';
    }
    
}
