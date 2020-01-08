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
    Role VARCHAR(50) NOT NULL,
    FOREIGN KEY fk_CastMembers_Actor (ActorID)
		REFERENCES Actor(ActorID),
	FOREIGN KEY fk_CastMembers_Movie (MovieID)
		REFERENCES Movie(MovieID)
);