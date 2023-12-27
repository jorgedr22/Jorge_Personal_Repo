package main.java;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * The DisbursementReportGenerator class extends ReportGenerator and is specifically
 * designed for generating disbursement reports based on scholarship data for a student.
 * @author Report Engine Team
 */
public class DisbursementReportGenerator extends ReportGenerator {

    //** The student for whom the disbursement report is generated. */
    private Student student;

    /** The scholarship for which the disbursement report is generated. */
    private Scholarship scholarship;

    /**
     * Constructs a DisbursementReportGenerator with the provided student and scholarship data.
     *
     * @param student The student who won the scholarship.
     * @param scholarship The scholarship won by the student.
     */
    public DisbursementReportGenerator(Student student, Scholarship scholarship) {
        this.filepath = "src/Reports/DisbursementReports/";
        this.student = student;
        this.scholarship = scholarship;
        this.filePrefix = "DisbursementReport";
    }

    /**
     * Writes the disbursement report to a CSV file.
     * Overrides the method in the superclass.
     *
     * @return The complete file path of the generated disbursement report.
     */
    @Override
    public String writeToFile() {
        try {
            String completeFilePath = this.filepath + filePrefix + "_" + student.getStudentID() + ".csv";
            File newAnnualReport = new File(completeFilePath);
            //System.out.println(newAnnualReport.createNewFile());
            FileWriter ReportWriter = new FileWriter(completeFilePath);
            ReportWriter.write(parseData());
            ReportWriter.close();
            //System.out.println("Report Generated");
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
        String reportString = "Scholarship Name, Student ID, Amount Rewarded, Disbursment Date\n";
        reportString = reportString + scholarship.getScholarshipName() + "," + student.getStudentID() + "," + scholarship.getPayout() + "," +
                scholarship.getDisbursementDate();
        return reportString;
    }
}
