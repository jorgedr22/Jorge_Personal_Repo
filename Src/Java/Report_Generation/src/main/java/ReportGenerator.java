package main.java;

import java.util.ArrayList;
import java.io.File;

/**
 * The is a superclass responsible for generating reports based on
 * scholarship and student data.
 * It provides methods to parse data, write reports to files, and create CSV
 * report files for different report types.
 * @author Report Engine Team
 */
public class ReportGenerator {
    /** The file path where the reports will be saved. */
    protected String filepath;

    /** The list of scholarships to be used in report generation. */
    public static ArrayList<Scholarship> scholarships;

    /** The list of students to be used in report generation. */
    public static ArrayList<Student> students;

    /**
     * Parses the data for report generation. 
     * Is overriden in subclasses
     * @return A string indicating that the parseData method is to be implemented.
     */
    public String parseData() {
        return "parseData TO BE IMPLEMENTED";
    }

    /** The prefix to be used for report file names. */
    protected String filePrefix;

    /**
     * Writes the generated reports to files.
     * Is implemented in subclasses
     */
    public String writeToFile() {
        System.out.println("writeToFile not yet implemented");
        return null;
    }
}
