import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.util.List;
import main.java.*;

/**
 * The DisbursementReportTest class is designed to test the functionality of the
 * DisbursementReportGenerator class by comparing generated disbursement reports
 * with expected results from test CSV files.
 */
public class DisbursementReportTest {

    /**
     * Reads the contents of a CSV file.
     *
     * @param filePath The path to the CSV file.
     * @return A List containing the CSV data as arrays of strings.
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
     * Compares two sets of CSV data to determine if they are identical.
     *
     * @param data1 The first set of CSV data.
     * @param data2 The second set of CSV data.
     * @return true if the data sets are identical, false otherwise.
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
     * The main method to execute the disbursement report tests.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        Student Student1 = new Student();
        Scholarship Scholarship1 = new Scholarship();
        
        //Disbursement Report Test 1  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest1.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship1.setScholarshipName(strings[0]);
                Student1.setStudentID(strings[1]);
                Scholarship1.setPayout(Integer.parseInt(strings[2]));
                Scholarship1.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo1 = new DisbursementReportGenerator(Student1,Scholarship1);
        final String DisbursementReportFileGen1 = DisbursementRepo1.writeToFile();
        final String DisbursementReportTestFile1 = "src/Test-Reports/DisbursementTest1.csv";
      
         try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(DisbursementReportFileGen1);
            List<String[]> DataCompare = readCSV(DisbursementReportTestFile1);

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

        Student Student2 = new Student();
        Scholarship Scholarship2 = new Scholarship();
        
        //Disbursement Report Test 2  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest2.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship2.setScholarshipName(strings[0]);
                Student2.setStudentID(strings[1]);
                Scholarship2.setPayout(Integer.parseInt(strings[2]));
                Scholarship2.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo2 = new DisbursementReportGenerator(Student2,Scholarship2);
        final String DisbursementReportFileGen2 = DisbursementRepo2.writeToFile();
        final String DisbursementReportTestFile2 = "src/Test-Reports/DisbursementTest2.csv";
      
         try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(DisbursementReportFileGen2);
            List<String[]> DataCompare = readCSV(DisbursementReportTestFile2);

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

        Student Student3 = new Student();
        Scholarship Scholarship3 = new Scholarship();
        //Disbursement Report Test 3  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest3.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship3.setScholarshipName(strings[0]);
                Student3.setStudentID(strings[1]);
                Scholarship3.setPayout(Integer.parseInt(strings[2]));
                Scholarship3.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo3 = new DisbursementReportGenerator(Student3,Scholarship3);
        final String DisbursementReportFileGen3 = DisbursementRepo3.writeToFile();
        final String DisbursementReportTestFile3 = "src/Test-Reports/DisbursementTest3.csv";
      
         try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(DisbursementReportFileGen3);
            List<String[]> DataCompare = readCSV(DisbursementReportTestFile3);

            // Compare data
            if (compareCSVData(DataRead, DataCompare)) {
                System.out.println("Test 3 Passed");
            } 
            else {
                System.out.println("Test 3 Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}