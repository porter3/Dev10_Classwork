USE TrackIt;

SELECT * FROM Worker
ORDER BY LastName ASC; -- ASC, DESC

SELECT
	w.FirstName,
    w.LastName,
    p.`Name` ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName ASC, p.Name ASC; -- Order by worker's last name in ascending (default) order, then the project name in ascending order

SELECT
	t.Title,
	s.`Name` StatusName
FROM Task t
LEFT OUTER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
ORDER BY ISNULL(StatusName), StatusName ASC; -- Puts the statuses with NULL values at the bottom


SELECT * FROM Worker
ORDER BY LastName DESC
LIMIT 10, 10;