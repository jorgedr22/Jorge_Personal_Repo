package main.java;

/**
 * This class represents a Scholarship with various attributes such as name, payout, deadline, etc.
 * The author of this class is backend.
 * @author Backend Team
 */
public class Student extends User {
    private String major;
    private double gpa;
    private String studentID;
    private String citizenship;
    private int unitsEnrolled;
    private String expectedGraduation;
    private String essayResponses; // FIXME: may need a new database for this or we make an array of long strings
                                   // here????
    private String bursarAccountReference;
    // FIXME: how do we want to do this -----> private scholarship
    // awardedScholarship;

    public Student() {// This is the default constructor for the student class
        this.major = "None";
        this.gpa = 0.0;
        this.studentID = "None";
        this.citizenship = "None";
        this.unitsEnrolled = 0;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    ////// Overloaded constructors for the student class //////
    public Student(String major) {
        this.major = major;
        this.gpa = 0.0;
        this.studentID = "None";
        this.citizenship = "None";
        this.unitsEnrolled = 0;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = "None";
        this.citizenship = "None";
        this.unitsEnrolled = 0;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = "None";
        this.unitsEnrolled = 0;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID, String citizenship) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = citizenship;
        this.unitsEnrolled = 0;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID, String citizenship, int unitsEnrolled) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = citizenship;
        this.unitsEnrolled = unitsEnrolled;
        this.expectedGraduation = "None";
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID, String citizenship, int unitsEnrolled,
            String expectedGraduation) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = citizenship;
        this.unitsEnrolled = unitsEnrolled;
        this.expectedGraduation = expectedGraduation;
        this.essayResponses = "None"; // FIXME: may need a new database for this or we make an array of long strings
                                      // here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID, String citizenship, int unitsEnrolled,
            String expectedGraduation, String essayResponses) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = citizenship;
        this.unitsEnrolled = unitsEnrolled;
        this.expectedGraduation = expectedGraduation;
        this.essayResponses = essayResponses; // FIXME: may need a new database for this or we make an array of long
                                              // strings here????
        this.bursarAccountReference = "None";
    }

    public Student(String major, double gpa, String studentID, String citizenship, int unitsEnrolled,
            String expectedGraduation, String essayResponses, String bursarAccountReference) {
        this.major = major;
        this.gpa = gpa;
        this.studentID = studentID;
        this.citizenship = citizenship;
        this.unitsEnrolled = unitsEnrolled;
        this.expectedGraduation = expectedGraduation;
        this.essayResponses = essayResponses; // FIXME: may need a new database for this or we make an array of long
                                              // strings here????
        this.bursarAccountReference = bursarAccountReference;
    }

    //// End overloaded constructors for the student class //////

    ////// Mututators and accessors for the student class //////
    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setUnitsEnrolled(int unitsEnrolled) {
        this.unitsEnrolled = unitsEnrolled;
    }

    public int getUnitsEnrolled() {
        return unitsEnrolled;
    }

    public void setExpectedGraduation(String expectedGraduation) {
        this.expectedGraduation = expectedGraduation;
    }

    public String getExpectedGraduation() {
        return expectedGraduation;
    }

    public void setEssayResponses(String essayResponses) {
        this.essayResponses = essayResponses;
    }

    public String getEssayResponses() {
        return essayResponses;
    }

    public void setBursarAccountReference(String bursarAccountReference) {
        this.bursarAccountReference = bursarAccountReference;
    }

    public String getBursarAccountReference() {
        return bursarAccountReference;
    }
    ////// End mututators and accessors for the student class //////

}
