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
    
INSERT INTO game(answer, finishedGame, gameStartTime) VALUES
('9999', false, '2020-01-17 15:36:30.000'),
('5000', false, '2021-01-17 15:36:30.000'),
('0001', false, '2020-02-17 15:36:30.000');

INSERT INTO round(gameID, userGuess, guessInfo, roundTimestamp) VALUES
(1, '5968', 'e:0:p:0', '2020-01-17 15:36:31.000'),
(1, '6000', 'e:0:p:0', '2020-01-17 15:36:31.000');

-- test queries
SELECT *
FROM round WHERE gameID = 1;

SELECT answer FROM game WHERE gameID = ?;
