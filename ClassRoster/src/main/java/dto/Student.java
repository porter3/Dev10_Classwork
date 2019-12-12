package dto;

/**
 *
 * @author jake
 */

// This is the data transfer object(DTO) that holds student info
public class Student {

    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort; // Java or .NET + cohort month/year ("Java, 12/19)
    
    public Student(String studentId){
        this.studentId = studentId; // read-only field, will have no setter method
        // all other fields must be set manually (not constructed)
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
    
    // only getter, no setter
    public String getStudentId(){
        return studentId;
    }
}
