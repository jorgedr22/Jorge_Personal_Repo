package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  // Import DateTimeFormatter
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * This class generates scholarship reports and writes them to CSV files.
 * The reports include information about scholarships, such as donor contact,
 * scholarship name, amount, and deadline.
 *
 * @author Report Engine Team
 */
public class ScholarshipReportGenerator extends ReportGenerator {
    private ArrayList<Scholarship> scholarshipData;
    protected String filepath;
    protected String filePrefix;
    protected String reportDate;
    protected static int fileNum = 1;

    /**
     * Constructs a ScholarshipReportGenerator with the specified list of scholarships.
     *
     * @param scholarship The list of scholarships to generate reports for.
     */
    public ScholarshipReportGenerator(ArrayList<Scholarship> scholarship) {
        this.filepath = "src/Reports/ScholarshipReports/";
        this.scholarshipData = scholarship;
        this.filePrefix = "ScholarshipReport";
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = localDate.format(formatter);
        this.reportDate = formattedString;
    }

    /**
     * Writes the scholarship report to a CSV file and returns the file path.
     *
     * @return The file path of the generated scholarship report.
     */
    public String writeToFile() {
        try {
            String completeFilePath = this.filepath + filePrefix + reportDate +" "+fileNum + ".csv";
            File newAnnualReport = new File(completeFilePath);
            //System.out.println(newAnnualReport.createNewFile());
            FileWriter ReportWriter = new FileWriter(completeFilePath);
            ReportWriter.write(parseData());
            ReportWriter.close();
            //System.out.println("Report Generated");
            ScholarshipReportGenerator.fileNum++;
            return completeFilePath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Parses scholarship data and returns a formatted string for the report.
     *
     * @return A formatted string containing scholarship data for the report.
     */
    public String parseData() {
        String reportString = "Donor Contact, Scholarship Name, Amount, Deadline\n";
        LocalDate currentDate = LocalDate.now();

        for (Scholarship data : scholarshipData) {  // Use the instance variable 
            reportString += data.getDonorContact() + "," + data.getScholarshipName() + "," + data.getPayout() + "," +data.getDeadline() + "\n";
        }
        return reportString;
    }
}
