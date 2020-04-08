DROP DATABASE IF EXISTS MovieCatalogue;

CREATE DATABASE MovieCatalogue;

USE MovieCatalogue;

CREATE TABLE Movie(
	-- foreign keys for genre, director, rating are added later
	MovieID INT PRIMARY KEY AUTO_INCREMENT,
    MovieGenreID INT NOT NULL,
    MovieDirectorID INT NULL,
    MovieRatingID INT NULL,
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE NULL
);

CREATE TABLE Genre(
	GenreID INT PRIMARY KEY AUTO_INCREMENT,
    GenreName VARCHAR(30) NOT NULL
);
    
CREATE TABLE Director(
	DirectorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE NULL
);

CREATE TABLE Rating(
	RatingID INT PRIMARY KEY AUTO_INCREMENT,
    RatingName CHAR(5) NOT NULL
);

ALTER TABLE Movie
	ADD CONSTRAINT fk_Movie_Genre
		FOREIGN KEY (MovieGenreID)
			REFERENCES Genre(GenreID),
	ADD CONSTRAINT fk_Movie_Director
		FOREIGN KEY (MovieDirectorID)
			REFERENCES Director(DirectorID),
	ADD CONSTRAINT fk_Movie_Rating
		FOREIGN KEY (MovieRatingID)
			REFERENCES Rating(RatingID);

CREATE TABLE Actor(
	ActorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE NULL
);

-- this is a bridge table. Actors can be in many movies, movies can have many actors. In this relationship, the actors are castmembers.
CREATE TABLE CastMembers(
	CastMemberID INT PRIMARY KEY AUTO_INCREMENT,
    ActorID INT NOT NULL,
    MovieID INT NOT NULL,
    ActorRole VARCHAR(50) NOT NULL,
    FOREIGN KEY fk_CastMembers_Actor (ActorID)
		REFERENCES Actor(ActorID),
	FOREIGN KEY fk_CastMembers_Movie (MovieID)
		REFERENCES Movie(MovieID)
);


-- DML Exercise breakline

INSERT INTO Actor(FirstName, LastName, BirthDate) VALUES
	('Bill', 'Murray', '1950-09-21'),
    ('Dan', 'Akroyd', '1952-07-01'),
    ('John', 'Candy', '1950-10-31'),
    ('Steve', 'Martin', NULL),
    ('Sylvester', 'Stallone', NULL);
        
INSERT INTO Director(FirstName, LastName, BirthDate) VALUES
	('Ivan', 'Reltman', '1946-10-27'),
    ('Ted', 'Kotcheff', NULL);

INSERT INTO Rating(RatingName) VALUES
	('G'), ('PG'), ('PG-13'), ('R');

INSERT INTO Genre(GenreName) VALUES
	('Action'), ('Comedy'), ('Drama'), ('Horror');

INSERT INTO Movie(MovieGenreId, MovieDirectorId, MovieRatingId, Title, ReleaseDate) VALUES
	(1, 2, 4, 'Rambo: First Blood', '1982-10-22'),
    (2, NULL,  4, 'Planes, Trains & Automobiles', '1987-11-25'),
    (2, 1, 2, 'Ghostbusters', NULL),
    (2, NULL, 2, 'The Great Outdoors', '1988-06-17');

INSERT INTO CastMembers(ActorID, MovieID, ActorRole) VALUES
	(5, 1, 'John Rambo'),
    (4, 2, 'Neil Page'),
    (3, 2, 'Del Griffith'),
    (1, 3, 'Dr. Peter Venkman'),
    (2, 3, 'Dr. Raymond Stanz'),
    (2, 4, 'Roman Craig'),
    (3, 4, 'Chet Ripley');
    
UPDATE Movie SET
	Title = 'Ghostbusters (1984)',
    ReleaseDate = '1984-06-08'
WHERE MovieID = 3;

DELETE FROM CastMembers
WHERE MovieID = 1;

DELETE FROM Movie
WHERE MovieID = 1;

ALTER TABLE Actor
	ADD COLUMN (DateOfDeath DATE NULL);

UPDATE Actor SET
	DateOfDeath = '1994-03-04'
WHERE ActorId = 3;