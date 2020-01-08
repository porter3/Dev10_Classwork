DROP DATABASE IF EXISTS TrackIt;
CREATE DATABASE TrackIt;

-- Make sure we're in the correct database before we add schema.
USE TrackIt;

CREATE TABLE Project (
    ProjectId CHAR(50) PRIMARY KEY,
    `Name` VARCHAR(100) NOT NULL,
    Summary VARCHAR(2000) NULL,
    DueDate DATE NOT NULL,
    IsActive BOOL NOT NULL DEFAULT 1
);

CREATE TABLE Worker (
    WorkerId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE ProjectWorker (
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    PRIMARY KEY pk_ProjectWorker (ProjectId, WorkerId),
    FOREIGN KEY fk_ProjectWorker_Project (ProjectId)
        REFERENCES Project(ProjectId),
    FOREIGN KEY fk_ProjectWorker_Worker (WorkerId)
        REFERENCES Worker(WorkerId)
);

CREATE TABLE Task (
    TaskId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    Details TEXT NULL,
    DueDate DATE NOT NULL,
    EstimatedHours DECIMAL(5, 2) NULL,
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    FOREIGN KEY fk_Task_ProjectWorker (ProjectId, WorkerId)
        REFERENCES ProjectWorker(ProjectId, WorkerId)
);


-- DML separator

-- Inserting without an explicit WorkerId (auto-incremented) gives it a value of '1' to start
INSERT INTO Worker (FirstName, LastName)
	VALUES ('Rosemonde', 'Featherbie');

INSERT INTO Worker (FirstName, LastName) VALUES
	('Kingsly', 'Besantie'),
    ('Goldi', 'Pilipets'),
    ('Dorey', 'Rulf'),
    ('Panchito', 'Ashtonhurst');

INSERT INTO Worker (WorkerId, FirstName, LastName)
	VALUES (50, 'Valentino', 'Newvill');
    -- inserting an explicit non-null auto-incremented value will cause the next auto-incremented one to be one higher (e.g. it will be 51)


INSERT INTO Project (ProjectId, `Name`, DueDate)
	VALUES ('db-milestone', 'Database Material', '2018-12-31');
    
-- assigning a worker to a project
INSERT INTO ProjectWorker (ProjectId, WorkerId)
	VALUES ('db-milestone', 2);

INSERT INTO Project (ProjectId, `Name`, DueDate)
	VALUES ('kitchen', 'Kitchen Remodel', '2025-07-15');
    
INSERT INTO ProjectWorker (ProjectId, WorkerId) VALUES
	('db-milestone', 1),
    ('kitchen', 2),
    ('db-milestone', 3),
    ('db-milestone', 4);
    
SET SQL_SAFE_UPDATES = 1;

UPDATE Project SET
	Summary = 'All lessons and exercises for the relational database milestone.',
    DueDate = '2018-10-15'
WHERE ProjectId = 'db-milestone';

UPDATE Worker SET
	LastName = 'Oaks'
WHERE WorkerId = 2;

SET SQL_SAFE_UPDATES = 0;

-- Deactivate active projects from 2017
UPDATE Project SET
	IsActive = 0
WHERE DueDate BETWEEN '2017-01-01' AND '2017-12-31'
AND IsActive = 1;

UPDATE Task SET
	EstimatedHours = EstimatedHours * 1.25
WHERE WorkerId = 2;

DELETE FROM Worker
WHERE WorkerId = 50;

-- Practice deleting a worker (Kingsley): must delete all records that reference the worker's primary key (WorkerId)
-- Delete Tasks first since Task references ProjectWorker
DELETE FROM Task
WHERE WorkerId = 2;

-- Deleting Kingsley from ProjectWorker next to remove him from all projects
DELETE FROM ProjectWorker
WHERE WorkerId = 2;

-- Finally, remove Kingsley from the Worker table
DELETE FROM Worker
WHERE WorkerId = 2;

SET SQL_SAFE_UPDATES = 1;