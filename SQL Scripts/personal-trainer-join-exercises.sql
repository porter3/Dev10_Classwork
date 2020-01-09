USE PersonalTrainer;

-- Select all columns from ExerciseCategory and Exercise.
-- The tables should be joined on ExerciseCategoryId.
-- This query returns all Exercises and their associated ExerciseCategory.
-- 64 rows
--------------------
SELECT *
FROM Exercise e
INNER JOIN ExerciseCategory ec
ON e.ExerciseCategoryId = ec.ExerciseCategoryId;
    
-- Select ExerciseCategory.Name and Exercise.Name
-- where the ExerciseCategory does not have a ParentCategoryId (it is null).
-- Again, join the tables on their shared key (ExerciseCategoryId).
-- 9 rows
--------------------
SELECT e.`Name`, ec.`Name`
FROM Exercise e
INNER JOIN ExerciseCategory ec
ON e.ExerciseCategoryId = ec.ExerciseCategoryId
WHERE ec.ParentCategoryId IS NULL;

-- The query above is a little confusing. At first glance, it's hard to tell
-- which Name belongs to ExerciseCategory and which belongs to Exercise.
-- Rewrite the query using an aliases. 
-- Alias ExerciseCategory.Name as 'CategoryName'.
-- Alias Exercise.Name as 'ExerciseName'.
-- 9 rows
--------------------
SELECT
e.`Name` ExerciseName,
ec.`Name` CategoryName
FROM Exercise e
INNER JOIN
ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId
WHERE ParentCategoryId IS NULL;

-- Select FirstName, LastName, and BirthDate from Client
-- and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
-- Join the tables by their key relationship. 
-- What is the primary-foreign key relationship?
	-- ANSWER: Login's PK is the same PK as Client's PK (ClientId), so it's a one-to-one relationships
-- 35 rows
--------------------
SELECT
`Client`.FirstName,
`Client`.LastName,
`Client`.BirthDate,
Login.EmailAddress
FROM `Client`
INNER JOIN Login ON `Client`.ClientId = `Login`.ClientId
WHERE `Client`.BirthDate BETWEEN '1990-01-01' AND '1999-12-31';

-- Select Workout.Name, Client.FirstName, and Client.LastName
-- for Clients with LastNames starting with 'C'?
-- How are Clients and Workouts related?
	-- ANSWER: Many Clients can have the same Workout, and a Workout can be used by many Clients (many-to-many).
		-- Connected via the bridge table ClientWorkout, which has records in the format of a client and one of their workouts.
-- 25 rows
--------------------
SELECT
Workout.`Name`,
`Client`.FirstName,
`Client`.LastName
FROM `Client`
INNER JOIN
ClientWorkout ON `Client`.ClientId = ClientWorkout.ClientId
INNER JOIN
Workout ON Workout.WorkoutId = ClientWorkout.WorkoutId
WHERE `Client`.LastName LIKE 'C%';

-- Select Names from Workouts and their Goals.
-- This is a many-to-many relationship with a bridge table.
-- Use aliases appropriately to avoid ambiguous columns in the result.
--------------------
SELECT
w.`Name` WorkoutName,
g.`Name` GoalName
FROM Workout w
INNER JOIN
WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
INNER JOIN Goal g ON g.GoalId = wg.GoalId;

-- Select FirstName and LastName from Client.
-- Select ClientId and EmailAddress from Login.
-- Join the tables, but make Login optional.
-- 500 rows
--------------------
SELECT
c.FirstName,
c.LastName,
l.ClientId,
l.EmailAddress
-- Still get the records from Client, even if they don't have a Login
FROM `Client` c
LEFT OUTER JOIN
Login l ON l.ClientId = c.ClientId;

-- Using the query above as a foundation, select Clients
-- who do _not_ have a Login.
-- 200 rows
--------------------
SELECT
c.FirstName,
c.LastName,
l.EmailAddress
FROM Client c
LEFT OUTER JOIN
Login l ON c.ClientId = l.ClientId
WHERE l.ClientId IS NULL;

-- Does the Client, Romeo Seaward, have a Login?
-- Decide using a single query.
-- nope :(
--------------------
SELECT
c.FirstName,
c.LastName,
l.EmailAddress
FROM `Client` c
-- using an outer left join because I want to return a record for Romeo Seaward, whether he has a login or not
LEFT OUTER JOIN
Login l ON l.ClientId = c.ClientId
WHERE c.FirstName = 'Romeo' and c.LastName = 'Seaward';

-- Select ExerciseCategory.Name and its parent ExerciseCategory's Name.
-- This requires a self-join.
-- 12 rows
--------------------
SELECT
parent.`Name`,
child.`Name`
FROM ExerciseCategory parent
INNER JOIN
ExerciseCategory child ON child.ParentCategoryId = parent.ExerciseCategoryId;
    
-- Rewrite the query above so that every ExerciseCategory.Name is
-- included, even if it doesn't have a parent.
-- 16 rows
--------------------
SELECT
parent.`Name`,
child.`Name`
FROM ExerciseCategory child
LEFT OUTER JOIN
ExerciseCategory parent ON child.ParentCategoryId = parent.ExerciseCategoryId;
    
-- Are there Clients who are not signed up for a Workout?
-- 50 rows
--------------------
-- i.e. you want to select ALL THE CLIENTS
	-- (and their corresponding workout records/lack thereof, whether they're signed up for a workout or not)
SELECT
c.ClientId,
w.`Name`
FROM `Client` c
LEFT OUTER JOIN
ClientWorkout cw ON c.ClientId = cw.ClientId
LEFT OUTER JOIN
Workout w ON w.WorkoutId = cw.WorkoutId
WHERE cw.WorkoutId IS NULL;


-- Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
-- Goals are associated to Clients through ClientGoal.
-- Goals are associated to Workouts through WorkoutGoal.
-- 6 rows, 4 unique rows
--------------------
SELECT
w.`Name`
FROM Client c
INNER JOIN ClientGoal cg ON cg.ClientId = c.ClientId
INNER JOIN Goal g ON g.GoalId = cg.GoalId
INNER JOIN WorkoutGoal wg ON wg.GoalId = g.GoalId
INNER JOIN Workout w ON w.WorkoutId = wg.WorkoutId
INNER JOIN `Level` l ON w.LevelId = l.LevelId
WHERE c.Firstname = 'Shell' AND c.LastName = 'Creane' and l.`Name` = 'Beginner';


-- Select all Workouts. 
-- Join to the Goal, 'Core Strength', but make it optional.
-- You may have to look up the GoalId before writing the main query.
-- If you filter on Goal.Name in a WHERE clause, Workouts will be excluded.
-- Why?
-- 26 Workouts, 3 Goals
--------------------
SELECT
GoalId
FROM Goal
WHERE Goal.`Name` = 'Core Strength';
-- goalID = 10

SELECT
w.`Name` WorkoutName,
g.`Name` GoalName
FROM Workout w
LEFT OUTER JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId AND GoalId = 10
LEFT OUTER JOIN Goal g ON g.GoalId = wg.GoalId;


-- The relationship between Workouts and Exercises is... complicated.
-- Workout links to WorkoutDay (one day in a Workout routine)
-- which links to WorkoutDayExerciseInstance 
-- (Exercises can be repeated in a day so a bridge table is required) 
-- which links to ExerciseInstance 
-- (Exercises can be done with different weights, repetions,
-- laps, etc...) 
-- which finally links to Exercise.
-- Select Workout.Name and Exercise.Name for related Workouts and Exercises.
--------------------
SELECT
w.`Name` WorkoutName,
e.`Name` ExerciseName
FROM
Workout w
INNER JOIN WorkoutDay wd ON wd.WorkoutId = w.WorkoutId
INNER JOIN WorkoutDayExerciseInstance wdei ON wdei.WorkoutDayId = wd.WorkoutDayId
INNER JOIN ExerciseInstance ei ON ei.ExerciseInstanceId = wdei.ExerciseInstanceId
INNER JOIN Exercise e ON e.ExerciseId = ei.ExerciseId;

   
-- An ExerciseInstance is configured with ExerciseInstanceUnitValue.
-- It contains a Value and UnitId that links to Unit.
-- Example Unit/Value combos include 10 laps, 15 minutes, 200 pounds.
-- Select Exercise.Name, ExerciseInstanceUnitValue.Value, and Unit.Name
-- for the 'Plank' exercise. 
-- How many Planks are configured, which Units apply, and what 
-- are the configured Values?
-- 4 rows, 1 Unit, and 4 distinct Values
--------------------
SELECT
e.`Name`,
eiuv.`Value`,
u.`Name`
FROM
Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue eiuv ON eiuv.ExerciseInstanceId = ei.ExerciseInstanceId
INNER JOIN Unit u ON u.UnitId = eiuv.UnitId
WHERE e.`Name` = 'Plank';
