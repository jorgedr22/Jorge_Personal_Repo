import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import main.java.Scholarship;
import main.java.Student;
import main.java.ApplicationData;
import main.java.GMailer;
import main.java.AnnualReportGenerator;
import main.java.MatchingReportGenerator;
import main.java.ScholarshipReportGenerator;
import main.java.DisbursementReportGenerator;
import main.java.ApplicationReportGenerator;

/**
 * The GMailerTest class is designed to test the functionality of the GMailer class
 * by simulating the generation and sending of various reports via email.
 * @author Report Engine Team
 */
public class GMailerTest {
    
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
     * The main method to execute the GMailer tests.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        /**
         * Reads the contents of a CSV file.
         *
         * @param filePath The path to the CSV file.
         * @return A List containing the CSV data as arrays of strings.
         * @throws IOException If an I/O error occurs while reading the CSV file.
         */
        final String EmailAddress = args[0];
        
        // Used for Report Email Tests
        ArrayList<Scholarship> AnnualRepoData = new ArrayList<Scholarship>();
        ArrayList<Scholarship> ScholarshipList = new ArrayList<Scholarship>();
        ArrayList<Scholarship> ScholarshipRepo = new ArrayList<Scholarship>();
        ArrayList<Student> StudentData = new ArrayList<Student>(); //could be used to check student name and ID
        Scholarship Scholarship = new Scholarship();
        Student Student = new Student();
        ApplicationData ApplicationReport = new ApplicationData();

        //----------------------------------------------------------------------------------------------------------------------------------------------//

        // Annual Report Email Test 1
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest1.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report");
            System.out.println(e.getMessage());
        }
         
        AnnualReportGenerator AnnualRepo1 = new AnnualReportGenerator(AnnualRepoData,2024);
        final String AnnualReportPath1 = AnnualRepo1.writeToFile();

        try {
            new GMailer().sendMail("Annual Report", "Here is the Annual Report", new File(AnnualReportPath1), EmailAddress);
            //System.out.println("Annual Report Sent! Please check your email inbox");
            System.out.println("Test 1 passed");
        } catch (Exception a) {
            //System.out.println("Annual Report NOT Sent.");
            System.out.println("Test 1 Failed");
            System.out.println(a.getMessage());
        }

        AnnualRepoData = new ArrayList<Scholarship>(); // reset for next test

        //----------------------------------------------------------------------------------------------------------------------------------------------//

        // Annual Report Email Test 2
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest2.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report");
            System.out.println(e.getMessage());
        }
         
        AnnualReportGenerator AnnualRepo2 = new AnnualReportGenerator(AnnualRepoData,2023);
        final String AnnualReportPath2 = AnnualRepo2.writeToFile();

        try {
            new GMailer().sendMail("Annual Report", "Here is the Annual Report", new File(AnnualReportPath2), EmailAddress);
            //System.out.println("Annual Report Sent! Please check your email inbox");
            System.out.println("Test 2 passed");
        } catch (Exception a) {
            //System.out.println("Annual Report NOT Sent.");
            System.out.println("Test 2 Failed");
            System.out.println(a.getMessage());
        }

        AnnualRepoData = new ArrayList<Scholarship>(); // reset for next test
        
        //----------------------------------------------------------------------------------------------------------------------------------------------//
        
        // Application Report Email Test 1
        Student.setName("Jorge Del Rio");
        Scholarship.setScholarshipName("Hitting The Griddy Scholarship");
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo1.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Question, Answer
                ApplicationReport.addPair(strings[0],strings[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ApplicationReportGenerator ApplicationRepo1 = new ApplicationReportGenerator(Scholarship,Student,ApplicationReport);
        final String ApplicationReportFile1 = ApplicationRepo1.writeToFile();
        
        try {
            new GMailer().sendMail("Applicant Report", "Here is the Applicant Report", new File(ApplicationReportFile1), EmailAddress);
            //System.out.println("Applicant Report Sent! Please check your email inbox");
            System.out.println("Test 3 passed");
        } catch (Exception a) {
            //System.out.println("Applicant Report NOT Sent.");
            System.out.println("Test 3 Failed");
            System.out.println(a.getMessage());
        }

        Scholarship = new Scholarship(); // reset for new test
        Student = new Student(); // reset for new test
        ApplicationReport = new ApplicationData(); // reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------//

        // Application Report Email Test 2
        Student.setName("Jorge Del Rio");
        Scholarship.setScholarshipName("What in the Griddy?");
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo2.csv");
            for (int i = 1; i < ReportData.size(); i++) {
                String[] strings = ReportData.get(i);
                //Question, Answer
                ApplicationReport.addPair(strings[0],strings[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
        
        ApplicationReportGenerator ApplicationRepo2 = new ApplicationReportGenerator(Scholarship,Student,ApplicationReport);
        final String ApplicationReportFile2 = ApplicationRepo2.writeToFile();
        
        try {
            new GMailer().sendMail("Applicant Report", "Here is the Applicant Report", new File(ApplicationReportFile2), EmailAddress);
            //System.out.println("Applicant Report Sent! Please check your email inbox");
            System.out.println("Test 4 passed");
        } catch (Exception a) {
            //System.out.println("Applicant Report NOT Sent.");
            System.out.println("Test 4 Failed");
            System.out.println(a.getMessage());
        }

        Scholarship = new Scholarship(); // reset for new test
        Student = new Student(); // reset for new test
        ApplicationReport = new ApplicationData(); // reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------//

        //Disbursement Report Email Test 1  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest1.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship.setScholarshipName(strings[0]);
                Student.setStudentID(strings[1]);
                Scholarship.setPayout(Integer.parseInt(strings[2]));
                Scholarship.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo1 = new DisbursementReportGenerator(Student,Scholarship);
        final String DisbursementReportFileGen1 = DisbursementRepo1.writeToFile();
        
        try {
            new GMailer().sendMail("Disbursement Report", "Here is the Disbursement Report", new File(DisbursementReportFileGen1), EmailAddress);
            //System.out.println("Disbursement Report Sent! Please check your email inbox");
            System.out.println("Test 5 passed");
        } catch (Exception a) {
            //System.out.println("Disbursement Report NOT Sent.");
            System.out.println("Test 5 Failed");
            System.out.println(a.getMessage());
        }

        Scholarship = new Scholarship(); // reset for new test
        Student = new Student(); // reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------//
        
        //Disbursement Report Email Test 2  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest2.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship.setScholarshipName(strings[0]);
                Student.setStudentID(strings[1]);
                Scholarship.setPayout(Integer.parseInt(strings[2]));
                Scholarship.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo2 = new DisbursementReportGenerator(Student,Scholarship);
        final String DisbursementReportFileGen2 = DisbursementRepo2.writeToFile();
        
        try {
            new GMailer().sendMail("Disbursement Report", "Here is the Disbursement Report", new File(DisbursementReportFileGen2), EmailAddress);
            //System.out.println("Disbursement Report Sent! Please check your email inbox");
            System.out.println("Test 6 passed");
        } catch (Exception a) {
            //System.out.println("Disbursement Report NOT Sent.");
            System.out.println("Test 6 Failed");
            System.out.println(a.getMessage());
        }

        Scholarship = new Scholarship(); // reset for new test
        Student = new Student(); // reset for new test
        
        //----------------------------------------------------------------------------------------------------------------------------------------------//
        
        //Disbursement Report Email Test 3  
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest3.csv");
            for (int i = 1; i < 2; i++) {
                String[] strings = ReportData.get(i);
                Scholarship.setScholarshipName(strings[0]);
                Student.setStudentID(strings[1]);
                Scholarship.setPayout(Integer.parseInt(strings[2]));
                Scholarship.setDisbursementDate(strings[3]);
            }
        }
        catch (Exception e) {
            System.out.println("Could not read Report " + e.getMessage());
        }
         
        DisbursementReportGenerator DisbursementRepo3 = new DisbursementReportGenerator(Student,Scholarship);
        final String DisbursementReportFileGen3 = DisbursementRepo3.writeToFile();
        
        try {
            new GMailer().sendMail("Disbursement Report", "Here is the Disbursement Report", new File(DisbursementReportFileGen3), EmailAddress);
            //System.out.println("Disbursement Report Sent! Please check your email inbox");
            System.out.println("Test 7 passed");
        } catch (Exception a) {
            //System.out.println("Disbursement Report NOT Sent.");
            System.out.println("Test 7 Failed");
            System.out.println(a.getMessage());
        }

        Scholarship = new Scholarship(); // reset for new test
        Student = new Student(); // reset for new test
        
        //----------------------------------------------------------------------------------------------------------------------------------------------//
 
        // Matching Report Email Test 1
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest1.csv");
            Student.setName("Jorge Del Rio");
            StudentData.add(Student);
            for (String[] ReportDataLine : ReportData) {
                // Start from index 1 to exclude the first index
                String value = ReportDataLine[2];
                // Process or use the value as needed
                //System.out.println(value); 
                if (value.equalsIgnoreCase("Yes")) {
                    ScholarshipList.add(new Scholarship(ReportDataLine[0],Integer.parseInt(ReportDataLine[1])));
                    // System.out.println(ScholarshipList.size());
                }
            }
        }

        catch (Exception e) {
            System.out.println("Could not read Report");
            System.out.println(e.getMessage());
        }

        MatchingReportGenerator MatchingRepo1 = new MatchingReportGenerator(ScholarshipList, StudentData);
        final String MatchingRepoFile1 = MatchingRepo1.writeToFile();

        //System.out.println(MatchingRepoFile1);

        try {
            new GMailer().sendMail("Matching Report", "Here is the Matching Report", new File(MatchingRepoFile1), EmailAddress);
            //System.out.println("Matching Report Sent! Please check your email inbox");
            System.out.println("Test 8 passed");
        } catch (Exception a) {
            //System.out.println("Matching Report NOT Sent.");
            System.out.println("Test 8 Failed");
            System.out.println(a.getMessage());
        }

        ScholarshipList = new ArrayList<Scholarship>();// reset for new test
        StudentData = new ArrayList<Student>();// reset for new test
        Student = new Student();// reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------/

        // Matching Report Email Test 2
       try {
            List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest2.csv");
            Student.setName("Jorge Del Rio");
            StudentData.add(Student);
            for (String[] ReportDataLine : ReportData) {
                // Start from index 1 to exclude the first index
                String value = ReportDataLine[2];
                // Process or use the value as needed
                //System.out.println(value); 
                if (value.equalsIgnoreCase("Yes")) {
                    ScholarshipList.add(new Scholarship(ReportDataLine[0],Integer.parseInt(ReportDataLine[1])));
                    // System.out.println(ScholarshipList.size());
                }
            }
        }

        catch (Exception e) {
            System.out.println("Could not read Report");
            System.out.println(e.getMessage());
        }

        MatchingReportGenerator MatchingRepo2 = new MatchingReportGenerator(ScholarshipList, StudentData);
        final String MatchingRepoFile2 = MatchingRepo2.writeToFile();

        //System.out.println(MatchingRepoFile1);

        try {
            new GMailer().sendMail("Matching Report", "Here is the Matching Report", new File(MatchingRepoFile2), EmailAddress);
            //System.out.println("Matching Report Sent! Please check your email inbox");
            System.out.println("Test 9 passed");
        } catch (Exception a) {
            //System.out.println("Matching Report NOT Sent.");
            System.out.println("Test 9 Failed");
            System.out.println(a.getMessage());
        }

        ScholarshipList = new ArrayList<Scholarship>();// reset for new test
        StudentData = new ArrayList<Student>();// reset for new test
        Student = new Student();// reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------/

        // Matching Report Email Test 3
        try {
            List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest3.csv");
            Student.setName("Jorge Del Rio");
            StudentData.add(Student);
            for (String[] ReportDataLine : ReportData) {
                // Start from index 1 to exclude the first index
                String value = ReportDataLine[2];
                // Process or use the value as needed
                //System.out.println(value); 
                if (value.equalsIgnoreCase("Yes")) {
                    ScholarshipList.add(new Scholarship(ReportDataLine[0],Integer.parseInt(ReportDataLine[1])));
                    // System.out.println(ScholarshipList.size());
                }
            }
        }

        catch (Exception e) {
            System.out.println("Could not read Report");
            System.out.println(e.getMessage());
        }

        MatchingReportGenerator MatchingRepo3 = new MatchingReportGenerator(ScholarshipList, StudentData);
        final String MatchingRepoFile3 = MatchingRepo3.writeToFile();

        //System.out.println(MatchingRepoFile1);

        try {
            new GMailer().sendMail("Matching Report", "Here is the Matching Report", new File(MatchingRepoFile3), EmailAddress);
            //System.out.println("Matching Report Sent! Please check your email inbox");
            System.out.println("Test 10 passed");
        } catch (Exception a) {
            //System.out.println("Matching Report NOT Sent.");
            System.out.println("Test 10 Failed");
            System.out.println(a.getMessage());
        }

        ScholarshipList = new ArrayList<Scholarship>();// reset for new test
        StudentData = new ArrayList<Student>();// reset for new test
        Student = new Student();// reset for new test

        //----------------------------------------------------------------------------------------------------------------------------------------------/
    
        // Scholarship Report Email Test 1
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
        
        try {
            new GMailer().sendMail("Scholarship Report", "Here is the Scholarship Report", new File(ScholarshipReportFile1), EmailAddress);
            //System.out.println("Scholarship Report Sent! Please check your email inbox");
            System.out.println("Test 11 passed");
        } catch (Exception a) {
            //System.out.println("Scholarship Report NOT Sent.");
            System.out.println("Test 11 Failed");
            System.out.println(a.getMessage());
        }

        ScholarshipRepo = new ArrayList<Scholarship>(); // reset for new tests

        //----------------------------------------------------------------------------------------------------------------------------------------------/
    
        // Scholarship Report Email Test 2
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
        
        try {
            new GMailer().sendMail("Scholarship Report", "Here is the Scholarship Report", new File(ScholarshipReportFile2), EmailAddress);
            //System.out.println("Scholarship Report Sent! Please check your email inbox");
            System.out.println("Test 12 passed");
        } catch (Exception a) {
            //System.out.println("Scholarship Report NOT Sent.");
            System.out.println("Test 12 Failed");
            System.out.println(a.getMessage());
        }

        ScholarshipRepo = new ArrayList<Scholarship>(); // reset for new tests

    }   
}
