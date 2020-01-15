USE meetingDB;

-- Selects room for specific meeting
SELECT
	r.*
FROM room r
INNER JOIN
meeting m ON m.roomID = r.ID
WHERE m.id = 1;

-- SELECT_MEETINGS_FOR_EMPLOYEE
SELECT *
FROM meeting m
INNER JOIN meeting_employee me ON me.meetingID = m.ID
WHERE me.employeeID = 1;