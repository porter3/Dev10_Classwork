package dao;

import dao.ClassRosterDao;
import dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jake
 */

// text file-specific implementation of the the ClassRosterDao interface
public class ClassRosterDaoFileImpl implements ClassRosterDao{
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) {
        Student newStudent = students.put(studentId, student);
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        // .values() is a map method
        // ArrayList constructor can take collections as parameters
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
        //remove student object from students (map)
        Student removedStudent = students.remove(studentId);
        return removedStudent;
    }
    
    //method to unmarshall(read, translate) a line of text into a student object
    private Student unmarshallStudent(String studentAsText){
    // take a string to break apart for the student info
    // split the string at delimiter
    String[] studentTokens = studentAsText.split(DELIMITER);
    // create a new Student
    String studentId = studentTokens[0];
    Student studentFromFile = new Student(studentId);
    // set each field of the new Student
    studentFromFile.setFirstName(studentTokens[1]);
    studentFromFile.setLastName(studentTokens[2]);
    studentFromFile.setCohort(studentTokens[3]);
    
    return studentFromFile;
    }
    
    //method to iterate over a file line by line and load students into the map
            // leaves the actual reading method up to unmarshallStudent()
    private void loadRoster() throws ClassRosterDaoException{
        Scanner scanner;
        
        try{
            // create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }
        // if file does not exist:
        catch (FileNotFoundException e){
            throw new ClassRosterDaoException("-_- Could not load roster into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Student currentStudent;
        // Go through ROSTER_FILE line by line, decoding each line into a Student object
            // by using unmarsahllStudent()
        while (scanner.hasNextLine()){
            // get next line in file
            currentLine = scanner.nextLine();
            // unmarshall that line
            currentStudent = unmarshallStudent(currentLine);
            
            // using the student ID as the Student Map key.
                // put currentStudent into the map
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close the scanner
        scanner.close();
    }
    
    //TODO: method to marshall a student into a line of text
    
    //TODO: method to iterate over all the students in the map and write them to a file
    
}
