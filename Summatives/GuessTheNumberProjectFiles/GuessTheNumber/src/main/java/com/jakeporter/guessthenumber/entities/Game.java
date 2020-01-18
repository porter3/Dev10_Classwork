package com.jakeporter.guessthenumber.entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author jake
 */
public class Game {

    private int gameId;
    private String answer;
    private boolean finishedGame;
    private Timestamp gameStartTime;    // maybe make these fields final later? Determine after DAO methods are written.

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    public Timestamp getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(Timestamp gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.gameId;
        hash = 67 * hash + Objects.hashCode(this.answer);
        hash = 67 * hash + (this.finishedGame ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.gameStartTime);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finishedGame != other.finishedGame) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.gameStartTime, other.gameStartTime)) {
            return false;
        }
        return true;
    }
    
}
