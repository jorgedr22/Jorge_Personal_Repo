package main.java;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import main.java.ApplicationData;
import main.java.Scholarship;
import main.java.Student;

/**
 * The ApplicationReportGenerator class extends ReportGenerator and is specifically designed
 * for generating application reports based on scholarship and student data.
 * @author Report Engine Team
 */
public class ApplicationReportGenerator extends ReportGenerator {

    /** The scholarship for which the application report is generated. */
    private Scholarship scholarship;

    /** The student for whom the application report is generated. */
    private Student student;

    /** The application data containing questions and answers. */
    private ApplicationData questions;

    /**
     * Constructs an ApplicationReportGenerator with the provided scholarship, student, and application data.
     *
     * @param scholarship The scholarship for the application report.
     * @param student The student for the application report.
     * @param data The application data containing questions and answers.
     */
    public ApplicationReportGenerator(Scholarship scholarship, Student student, ApplicationData data) {
        this.filepath = "src/Reports/ApplicationReports/";
        this.scholarship = scholarship;
        this.student = student;
        this.questions = data;
        this.filePrefix = scholarship.getScholarshipName();
    }

    /**
     * Writes the application report to a CSV file.
     * Overrides the method in the superclass.
     *
     * @return A string with the filepath to the newly created file.
     */
    @Override
    public String writeToFile() {
        try {
            String completeFilePath = this.filepath + filePrefix + "_" + student.getName() +".csv";
            File newAnnualReport = new File(completeFilePath);
            //System.out.println(newAnnualReport.createNewFile());
            FileWriter ReportWriter = new FileWriter(completeFilePath);
            ReportWriter.write(parseData());
            //System.out.println("Printed " + parseData());
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
        String reportString = "Question, Answer\n";
        for(int i = 0; i < questions.getApplicationDataSet().size(); i++) {
            reportString += questions.getApplicationDataSet().get(i).get(0) + "," + questions.getApplicationDataSet().get(i).get(1) + "\n";
        }
        return reportString;
    }
}
