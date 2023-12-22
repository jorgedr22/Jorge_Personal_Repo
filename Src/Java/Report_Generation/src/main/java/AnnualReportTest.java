import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.util.List;
import main.java.*;

/**
 * The AnnualReportTest class is used to test the functionality of the AnnualReportGenerator class
 * by comparing generated annual reports with expected reports read from CSV files.
 * @author Report Engine Team
 */
public class AnnualReportTest {
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
     * The main method to run the AnnualReportTest application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        
        ArrayList<Scholarship> AnnualRepoData = new ArrayList<Scholarship>();
        
        //Annual Report Test 1  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest1.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report");
        }
         
        AnnualReportGenerator AnnualRepo1 = new AnnualReportGenerator(AnnualRepoData,2024);
        final String AnnualReportFileGen1 = AnnualRepo1.writeToFile();
        final String AnnualReportTestFile1 = "src/Test-Reports/AnnualReportTest1.csv";
      
         try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(AnnualReportFileGen1);
            List<String[]> DataCompare = readCSV(AnnualReportTestFile1);

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

        //Annual Report Test 2  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest2.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report");
        }
          
        AnnualReportGenerator AnnualRepo2 = new AnnualReportGenerator(AnnualRepoData,2023);
        final String AnnualReportFileGen2 = AnnualRepo2.writeToFile();
        final String AnnualReportTestFile2 = "src/Test-Reports/AnnualReportTest2.csv";
      
         try {
            // Read data from CSV files
            List<String[]> DataRead = readCSV(AnnualReportFileGen2);
            List<String[]> DataCompare = readCSV(AnnualReportTestFile2);

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