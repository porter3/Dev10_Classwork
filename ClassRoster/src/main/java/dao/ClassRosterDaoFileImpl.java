package dao;

import dao.ClassRosterDao;
import dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        // load students into map
        loadRoster();
        Student newStudent = students.put(studentId, student);
        // writes all students to roster.txt
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        // .values() is a map method
        // ArrayList constructor can take collections as parameters
        // loadRoster() gets all students and loads them into the map
        loadRoster(); // ASK SHAWN about details of the error I get here if I choose not to throw the exception in the method declaration
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        // load all students into the map
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        //remove student object from students (map)
        Student removedStudent = students.remove(studentId);
        // write updated map of students to list and then to file
        writeRoster();
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
    private void loadRoster() throws ClassRosterPersistenceException{
        Scanner scanner;
        
        try{
            // create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }
        // if file does not exist:
        catch (FileNotFoundException e){
            throw new ClassRosterPersistenceException("-_- Could not load roster into memory.", e);
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
    
    //method to marshall a student into a line of text
        // MUST preserve order of information when translating Student into text
    private String marshallStudent(Student aStudent){
        // could probably use String.format() but sticking w/ concantenation for now
        String studentAsText = aStudent.getStudentId() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();
        return studentAsText; 
   }
    
    //TODO: method to iterate over all the students in the map and write them to a file
    private void writeRoster() throws ClassRosterPersistenceException{
        // We are not handling the IOException - but we are translating it to an
            // application-specific exception and then reporting it to the code
            // that called it. It's the calling code's responsibility to handle
            // potential errors.
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        catch (IOException e){
            throw new ClassRosterPersistenceException("Could not save student data", e);
        }
        
        // write out the student objects to roster.txt
        String studentsAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList){
            // turn Student into a string
            String studentAsText = marshallStudent(currentStudent);
            // write string to file
            out.println(studentAsText);
            // force PrintWriter to write remaining line to file
            out.flush();
        }
        // clean up resources
        out.close();
    }
}
