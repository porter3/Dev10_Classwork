USE PersonalTrainer;

-- Select all rows and columns from the Exercise table.
-- 64 rows
--------------------
SELECT *
FROM exercise;

-- Select all rows and columns from the Client table.
-- 500 rows
--------------------
SELECT *
FROM client
LIMIT 0,500;

-- Select all columns from Client where the City is Metairie.
-- 29 rows
--------------------
SELECT *
FROM client
WHERE City = 'Metairie';

-- Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'?
-- nope
SELECT ClientId
FROM client
WHERE ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';

-- How many rows in the Goal table?
-- 17 rows
--------------------
SELECT GoalId
FROM goal;

-- Select Name and LevelId from the Workout table.
-- 26 rows
--------------------
SELECT Name, LevelId
FROM workout;


-- Select Name, LevelId, and Notes from Workout where LevelId is 2.
-- 11 rows
--------------------
SELECT Name, LevelId, Notes
FROM workout
WHERE LevelId = 2;

-- Select FirstName, LastName, and City from Client 
-- where City is Metairie, Kenner, or Gretna.
-- 77 rows
--------------------
SELECT FirstName, LastName, City
FROM client
WHERE City IN ('Metairie', 'Kenner', 'Gretna');

-- Select FirstName, LastName, and BirthDate from Client
-- for Clients born in the 1980s.
-- 72 rows
--------------------
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate < '1990-01-01' AND BirthDate > '1979-12-31';

-- Write the query above in a different way. 
-- If you used BETWEEN, you can't use it again.
-- If you didn't use BETWEEN, use it!
-- Still 72 rows
--------------------
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate BETWEEN '1979-12-31' AND '1990-01-01';

-- How many rows in the Login table have a .gov EmailAddress?
-- 17 rows
--------------------
SELECT ClientId, EmailAddress
FROM login
WHERE EmailAddress LIKE '%.gov';

-- How many Logins do NOT have a .com EmailAddress?
-- 122 rows
--------------------
SELECT ClientID, EmailAddress
FROM login
WHERE EmailAddress NOT LIKE '%.com';

-- Select first and last name of Clients without a BirthDate.
-- 37 rows
--------------------
SELECT FirstName, LastName
FROM clients
WHERE BirthDate IS NULL;

-- Select the Name of each ExerciseCategory that has a parent.
-- (ParentCategoryId value is not null)
-- 12 rows
--------------------
SELECT Name
FROM exercisecategory
WHERE ParentCategoryId IS NOT NULL;

-- Select Name and Notes of each level 3 Workout that
-- contains the word 'you' in its Notes.
-- 4 rows
--------------------
SELECT Name, Notes
FROM workout
WHERE LevelId = 3 AND Notes LIKE '%you%';

-- Select FirstName, LastName, City from Clients who have a LastName
-- that starts with L,M, or N and who live in LaPlace.
-- 5 rows
--------------------
SELECT FirstName, LastName, City
FROM client
WHERE (LastName LIKE 'L%'
OR LastName LIKE 'M%' 
OR LastName LIKE 'N%')
AND City = 'LaPlace';

-- Select InvoiceId, Description, Price, Quantity, ServiceDate 
-- and the line item total, a calculated value, where the line item total
-- is between 15 and 25 dollars.
-- 667 rows
--------------------
SELECT InvoiceId, Description, Price, Quantity, ServiceDate, Price*Quantity
FROM invoicelineitem
WHERE (Price*Quantity) BETWEEN 15 AND 25;

-- Does the Client, Estrella Bazely, have a Login? 
-- If so, what is her email address?
-- This requires two queries:
-- First, select a Client record for Estrella Bazely. Does it exist?
-- Second, if it does, select a Login record that matches its ClientId.
--------------------
SELECT ClientId
FROM client
WHERE FirstName = 'Estrella' AND LastName = 'Bazely';
SELECT *
FROM login
WHERE ClientId = '87976c42-9226-4bc6-8b32-23a8cd7869a5';

-- What are the Goals of the Workout with the Name 'This Is Parkour'?
-- You need three queries!:
-- 1. Select the WorkoutId from Workout where Name equals 'This Is Parkour'.
-- 2. Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId
--    from your first query.
-- 3. Select everything from Goal where the GoalId is one of the GoalIds from your
--    second query.
-- 1 row, 3 rows, 3 rows
--------------------
SELECT WorkoutId
FROM workout
WHERE Name = 'This is Parkour';
SELECT goalId
FROM workoutgoal
WHERE WorkoutId = 12;
SELECT *
FROM goal
WHERE goalId IN (3,8,15);