import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.util.List;
import main.java.*;

/**
 * This class contains test cases for the ScholarshipReportGenerator.
 * @author Report Engine Team
 */
public class ScholarshipReportTest{

    /**
     * Reads CSV data from the specified file path.
     *
     * @param filePath The path of the CSV file to read.
     * @return A list of arrays representing the data read from the CSV file.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    private static List<String[]> readCSV(String filePath) throws IOException {
    
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
        return reader.readAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Compares two lists of CSV data and determines if they are identical.
     *
     * @param data1 The first list of CSV data.
     * @param data2 The second list of CSV data for comparison.
     * @return true if the lists are identical, false otherwise.
     */
    private static boolean compareCSVData(List<String[]> data1, List<String[]> data2) {
        if (data1.size() != data2.size()) {
            return false; // Different number of rows
        }

        for (int i = 0; i < data1.size(); i++) {
            String[] row1 = data1.get(i);
            String[] row2 = data2.get(i);

            if (!Arrays.equals(row1, row2)) {
                return false; // Rows are different
            }
        }

        return true; // All rows are identical
    }

    /**
     * The main method that runs the ScholarshipReportGenerator test cases.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        ArrayList<Scholarship> ScholarshipRepo = new ArrayList<Scholarship>();

        // Test 1
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ScholarshipInfo1.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Donor Contact, Scholarship Name, Amount, Deadline
                ScholarshipRepo.add(new Scholarship(strings[1],Integer.parseInt(strings[2]),strings[3],"NA","NA","NA",strings[0]));  
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ScholarshipReportGenerator ScholarshipRepo1 = new ScholarshipReportGenerator(ScholarshipRepo);
        final String ScholarshipReportFile1 = ScholarshipRepo1.writeToFile();
        final String ScholarshipReportTestFile1 = "src/Test-Reports/ScholarshipInfo1.csv";

        try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(ScholarshipReportFile1);
            List<String[]> DataCompare = readCSV(ScholarshipReportTestFile1);

            // Compare data
            if (compareCSVData(DataRead, DataCompare)) {
                System.out.println("Test 1 Passed");
            } 
            else {
                System.out.println("Test 1 Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScholarshipRepo = new ArrayList<Scholarship>();

        // Test 2
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ScholarshipInfo2.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Donor Contact, Scholarship Name, Amount, Deadline
                ScholarshipRepo.add(new Scholarship(strings[1],Integer.parseInt(strings[2]),strings[3],"NA","NA","NA",strings[0]));  
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ScholarshipReportGenerator ScholarshipRepo2 = new ScholarshipReportGenerator(ScholarshipRepo);
        final String ScholarshipReportFile2 = ScholarshipRepo2.writeToFile();
        final String ScholarshipReportTestFile2 = "src/Test-Reports/ScholarshipInfo2.csv";

        try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(ScholarshipReportFile2);
            List<String[]> DataCompare = readCSV(ScholarshipReportTestFile2);

            // Compare data
            if (compareCSVData(DataRead, DataCompare)) {
                System.out.println("Test 2 Passed");
            } 
            else {
                System.out.println("Test 2 Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}