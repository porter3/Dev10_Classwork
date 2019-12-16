package dao;

/**
 *
 * @author jake
 */

// Error class specific to this application
public class ClassRosterDaoException extends Exception{

    // QUESTION: if I didn't explicitly add these, wouldn't super() just get called with either
        // of these overloaded methods?
    
    // use when something is wrong but it isn't caused by another exception (i.e. one of the fields for student data input isn't verified correctly)
    public ClassRosterDaoException(String message){
        super(message);
    }
    
    // use when something is wrong and IT IS caused by another exception
    // note that the second parameter is "Throwable" and not "Exception" (its subclass)
        // This is because the base class can handle more errors than Throwable
    public ClassRosterDaoException(String messsage, Throwable cause){
        super(messsage, cause);
    }
}
