import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.util.List;
import main.java.*;

/**
 * The ApplicationReportTest class is used to test the functionality of the ApplicationReportGenerator class
 * by comparing generated application reports with expected reports read from CSV files.
 * @author Report Engine Team
 */
public class ApplicationReportTest {
    
    /**
     * Reads data from a CSV file and returns it as a list of string arrays.
     *
     * @param filePath The path to the CSV file.
     * @return A list of string arrays representing the data in the CSV file.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    private static List<String[]> readCSV(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

     /**
     * Compares two lists of string arrays to check if their contents are identical.
     *
     * @param data1 The first list of string arrays.
     * @param data2 The second list of string arrays.
     * @return true if the lists have identical contents, false otherwise.
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
     * The main method to run the ApplicationReportTest application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        
        Scholarship Scholarship1 = new Scholarship();
        Student Student1 = new Student();
        ApplicationData ApplicationReport1 = new ApplicationData();
         
        // Test 1
        Student1.setName("Jorge Del Rio");
        Scholarship1.setScholarshipName("Hitting The Griddy Scholarship");
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo1.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Question, Answer
                ApplicationReport1.addPair(strings[0],strings[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ApplicationReportGenerator ApplicationRepo1 = new ApplicationReportGenerator(Scholarship1,Student1,ApplicationReport1);
        final String ApplicationReportFile1 = ApplicationRepo1.writeToFile();
        final String ApplicationReportTestFile1 = "src/Test-Reports/ApplicationInfo1.csv";

        try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(ApplicationReportFile1);
            List<String[]> DataCompare = readCSV(ApplicationReportTestFile1);

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

        Scholarship Scholarship2 = new Scholarship();
        Student Student2 = new Student();
        ApplicationData ApplicationReport2 = new ApplicationData();

        // Test 2
        Student2.setName("Jorge Del Rio");
        Scholarship2.setScholarshipName("What In The Graddle?");
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo2.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Question, Answer
                ApplicationReport2.addPair(strings[0],strings[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ApplicationReportGenerator ApplicationRepo2 = new ApplicationReportGenerator(Scholarship2,Student2,ApplicationReport2);
        final String ApplicationReportFile2 = ApplicationRepo2.writeToFile();
        final String ApplicationReportTestFile2 = "src/Test-Reports/ApplicationInfo2.csv";

        try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(ApplicationReportFile2);
            List<String[]> DataCompare = readCSV(ApplicationReportTestFile2);

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
