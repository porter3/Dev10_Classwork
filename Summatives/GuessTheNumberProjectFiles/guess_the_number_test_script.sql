DROP DATABASE IF EXISTS guessthenumbertest;
CREATE DATABASE IF NOT EXISTS guessthenumbertest;
USE guessthenumbertest;

CREATE TABLE game(
	gameID INT PRIMARY KEY AUTO_INCREMENT,
    answer CHAR(4) NOT NULL,
    finishedGame BOOLEAN NOT NULL,
    gameStartTime DATETIME NOT NULL
);

-- STRETCH GOAL: Make this table's primary key a compound key using roundID and gameID
CREATE TABLE round(
	roundID INT PRIMARY KEY AUTO_INCREMENT,
    gameID INT NOT NULL,
    userGuess CHAR(4) NOT NULL,
    guessInfo CHAR(7) NOT NULL,
    roundTimestamp DATETIME NOT NULL,
    FOREIGN KEY fk_round_game(gameID)
		REFERENCES game(gameID)
    );