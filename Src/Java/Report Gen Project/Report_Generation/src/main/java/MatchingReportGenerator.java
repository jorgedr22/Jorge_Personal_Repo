package main.java;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.common.escape.ArrayBasedCharEscaper;

import main.java.Scholarship;
import main.java.Student;

/**
 * The MatchingReportGenerator class extends ReportGenerator and is specifically
 * designed for generating matching reports based on scholarship data and student information.
 * @author Report Generator Team
 */
public class MatchingReportGenerator extends ReportGenerator {

    /** The year for which the annual report is generated. */
    private ArrayList<Scholarship> scholarshipData = new ArrayList<Scholarship>();

    /** The list of students for the matching report. */
    private ArrayList<Student> studentData = new ArrayList<Student>();

    private static int fileNum = 1;
    

    /**
     * Constructs a MatchingReportGenerator with the provided scholarship and student data.
     *
     * @param scholarshipData The list of scholarships for the matching report.
     * @param studentData The list of students for the matching report.
     */
    public MatchingReportGenerator(ArrayList<Scholarship> scholarshipData, ArrayList<Student> studentData) {
        this.filepath = "src/Reports/MatchingReports/";
        this.scholarshipData = scholarshipData;
        this.studentData = studentData;
        this.filePrefix = "MatchingReport";
    }

    /**
     * Writes the annual report to a CSV file.
     * Overrides the method in the superclass.
     * @return The completed filepath to the generated file
     */
    @Override
    public String writeToFile() {
        try {
            String completeFilePath = this.filepath + filePrefix + "_" + studentData.get(0).getName() + "_"+fileNum+ ".csv";
            File newAnnualReport = new File(completeFilePath);
            //System.out.println(newAnnualReport.createNewFile());
            FileWriter ReportWriter = new FileWriter(completeFilePath);
            ReportWriter.write(parseData());
            //System.out.println("Printed " + parseData());
            ReportWriter.close();
            //System.out.println("Report Generated");
            fileNum++;
            return completeFilePath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Parses scholarship data for the annual report.
     * Overrides the method in the superclass.
     *
     * @return A string representation of the scholarship data in CSV format.
     */

    @Override
    public String parseData() {
        String reportString = "Scholarship name, Amount\n";
        for(int i = 0; i < scholarshipData.size(); i++) {
            reportString += scholarshipData.get(i).getScholarshipName() + "," + scholarshipData.get(i).getPayout() +"\n";
        }
        return reportString;
    }
}
