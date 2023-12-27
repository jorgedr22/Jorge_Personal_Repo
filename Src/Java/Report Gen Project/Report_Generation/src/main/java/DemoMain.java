import javax.swing.*;
import com.opencsv.CSVReader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.java.JTextFieldWithPlaceholder;
import main.java.Scholarship;
import main.java.ScholarshipReportGenerator;
import main.java.Student;
import main.java.AnnualReportGenerator;
import main.java.ApplicationReportGenerator;
import main.java.DisbursementReportGenerator;
import main.java.GMailer;
import main.java.ApplicationData;
import main.java.MatchingReportGenerator;

/**
 * The DemoMain class represents a Swing GUI application for generating scholarship-related reports
 * and sending them via email. It includes functionality to generate annual reports, disbursement reports,
 * scholarship reports, and application reports. It also allows the generation of random scholarship data.
 * All data and file creation is done using dummy data
 * @author Report Engine Team
 */
public class DemoMain extends JFrame {

    private JTextField emailField;
    private JTextField nameField;
    private JTextField yearField;
    //private JTextField scholarshipsField;
    public ArrayList<Scholarship> scholarshipData;
    //private JTextField studentsField;

    private static List<String[]> readCSV(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Constructs a new instance of the DemoMain class, initializing the GUI components.
     */
    public DemoMain() {
        super("Scholarship Report Generator Demo Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Email Address
        JLabel emailLabel = new JLabel("Email Address:");
        emailField = new JTextFieldWithPlaceholder("Enter your email");

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextFieldWithPlaceholder("Enter your name");

        // Yeay
        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextFieldWithPlaceholder("Enter year:");

        // Add components to the panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(yearLabel);
        panel.add(yearField);
        //panel.add(scholarshipsLabel);
        //panel.add(scholarshipsField);
        //panel.add(studentsLabel);
        //panel.add(studentsField);


        // Submit Button
        JButton annualReportButton = new JButton("Generate Annual Report");
        annualReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        ArrayList<Scholarship> AnnualRepoData = new ArrayList<Scholarship>();
                        
                        try {
                            if(Integer.parseInt(yearField.getText())==2023){
                                List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest2.csv");
                                for (int i = 1; i < ReportData.size(); i++) {
                                    String[] strings = ReportData.get(i);
                                    AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
                                }
                            }
                            else if(Integer.parseInt(yearField.getText())==2024){
                                List<String[]> ReportData = readCSV("src/Test-Reports/AnnualReportTest1.csv");
                                for (int i = 1; i < ReportData.size(); i++) {
                                    String[] strings = ReportData.get(i);
                                    AnnualRepoData.add(new Scholarship(strings[0],Integer.parseInt(strings[1]),strings[2],strings[3],strings[4],strings[5]));    
                                }
                            }
                        }
                        catch (Exception a) {
                            System.out.println("Could not read Report");
                            System.out.println(a.getMessage());
                        }

                        AnnualReportGenerator generator = new AnnualReportGenerator(AnnualRepoData, Integer.parseInt(yearField.getText()));
                        String path = generator.writeToFile();
                        
                        try {
                            new GMailer().sendMail("Annual Report " + yearField.getText(), "This is the Annual Report for the year "+yearField.getText(), new File(path), emailField.getText());
                            System.out.println("Annual Report test passed");
                        } catch (Exception a) {
                            System.out.println(a.getMessage());
                        }
                    }
                });

        // Submit Button
        JButton disbursementButton = new JButton("Generate Disbursement Report");
        disbursementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student Student = new Student();
                Scholarship Scholarship = new Scholarship();
        
                try {
                    if(nameField.getText().equalsIgnoreCase("Jorge Del Rio")){
                        List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest1.csv");
                        for (int i = 1; i < 2; i++) {
                            String[] strings = ReportData.get(i);
                            Scholarship.setScholarshipName(strings[0]);
                            Student.setStudentID(strings[1]);
                            Scholarship.setPayout(Integer.parseInt(strings[2]));
                            Scholarship.setDisbursementDate(strings[3]);
                        }
                    }
                    else if (nameField.getText().equalsIgnoreCase("Sharon O'Neal")) {
                        List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest2.csv");
                        for (int i = 1; i < 2; i++) {
                            String[] strings = ReportData.get(i);
                            Scholarship.setScholarshipName(strings[0]);
                            Student.setStudentID(strings[1]);
                            Scholarship.setPayout(Integer.parseInt(strings[2]));
                            Scholarship.setDisbursementDate(strings[3]);
                        }
                    }
                    else {
                        List<String[]> ReportData = readCSV("src/Test-Reports/DisbursementTest3.csv");
                        for (int i = 1; i < 2; i++) {
                            String[] strings = ReportData.get(i);
                            Scholarship.setScholarshipName(strings[0]);
                            Student.setStudentID(strings[1]);
                            Scholarship.setPayout(Integer.parseInt(strings[2]));
                            Scholarship.setDisbursementDate(strings[3]);
                        }
                    }
                }
                catch (Exception a) {
                    System.out.println("Could not read Report " + a.getMessage());
                }     
                
                DisbursementReportGenerator generator = new DisbursementReportGenerator(Student, Scholarship);
                String path = generator.writeToFile();
                //System.out.println("Generated " + scholarshipData.size() + " Scholarships");
                
                try {
                    new GMailer().sendMail("Disbursement Report", "This is the Disbursement for " + Student.getName(), new File(path), emailField.getText());
                    System.out.println("Disbursement Report test passed");
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }
            }
        });

        // Submit Button
        JButton scholarshipButton = new JButton("Generate Scholarship Report");
        scholarshipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Scholarship> ScholarshipRepo = new ArrayList<Scholarship>();

                try {
                    if(nameField.getText().equalsIgnoreCase("Jorge Del Rio")){
                        List<String[]> ReportData = readCSV("src/Test-Reports/ScholarshipInfo1.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            //Donor Contact, Scholarship Name, Amount, Deadline
                            ScholarshipRepo.add(new Scholarship(strings[1],Integer.parseInt(strings[2]),strings[3],"NA","NA","NA",strings[0]));  
                        }
                    }
                    else{
                        List<String[]> ReportData = readCSV("src/Test-Reports/ScholarshipInfo2.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            //Donor Contact, Scholarship Name, Amount, Deadline
                            ScholarshipRepo.add(new Scholarship(strings[1],Integer.parseInt(strings[2]),strings[3],"NA","NA","NA",strings[0]));  
                        }
                    }
                }
                catch (Exception a) {
                    System.out.println("Could not read Report " + a.getMessage());
                }
                
                ScholarshipReportGenerator generator = new ScholarshipReportGenerator(ScholarshipRepo);
                String path = generator.writeToFile();
                try {
                    new GMailer().sendMail("Scholarship Report", "We are the best Report Team!", new File(path), emailField.getText());
                    System.out.println("Scholarship Report test passed");
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }
            }
        });

        // Submit Button
        JButton applicationButton = new JButton("Generate Application Report");
        applicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Scholarship Scholarship = new Scholarship();
                Student Student = new Student();
                ApplicationData ApplicationReport = new ApplicationData();

                // Handle submission logic here
                String email = emailField.getText();
                String name = nameField.getText();
                //String scholarships = scholarshipsField.getText();

                Student.setName(name);
                try {
                    if(name.equalsIgnoreCase("Jorge Del Rio")){
                        Scholarship.setScholarshipName("Sharon Donates to Broke College Students");
                        List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo1.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            //Question, Answer
                            ApplicationReport.addPair(strings[0],strings[1]);
                        }
                    }
                    else {
                        Scholarship.setScholarshipName("Software Engineering Scholarship 2023-2024");
                        List<String[]> ReportData = readCSV("src/Test-Reports/ApplicationInfo2.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            //Question, Answer
                            ApplicationReport.addPair(strings[0],strings[1]);
                        }
                    }
                }
                catch (Exception a) {
                    System.out.println("Could not read Report " + a.getMessage());
                }
                
                ApplicationReportGenerator generator = new ApplicationReportGenerator(Scholarship,Student,ApplicationReport);
                String path = generator.writeToFile();
                try {
                    new GMailer().sendMail("Applicant Report", "These are the answers from "+Student.getName()+" to the scholarship "+Scholarship.getScholarshipName(), new File(path), email);
                    System.out.println("Applicant Report test passed");
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }
            }
        });
        JButton matchingButton = new JButton("Generate Matching Report");
        matchingButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e){
            
                ArrayList<Scholarship> ScholarshipList1 = new ArrayList<Scholarship>();
                ArrayList<Student> Student1 = new ArrayList<Student>(); //could be used to check student name and ID
                Student testStudent1 = new Student();

                testStudent1.setName(nameField.getText());
                Student1.add(0,testStudent1);
                
                // Matching Report Test 1
                try {
                    if(nameField.getText().equalsIgnoreCase("Samuel Moreno")){
                        List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest3.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            if (strings[2].equalsIgnoreCase("Yes")) {
                                ScholarshipList1.add(new Scholarship(strings[0],Integer.parseInt(strings[1])));
                            }
                        }
                    }
                    else if (nameField.getText().equalsIgnoreCase("Jorge Del Rio")) {
                        List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest2.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            if (strings[2].equalsIgnoreCase("Yes")) {
                                ScholarshipList1.add(new Scholarship(strings[0],Integer.parseInt(strings[1])));
                            }
                        }
                    }
                    else {
                        List<String[]> ReportData = readCSV("src/Test-Reports/MatchingReportTest1.csv");
                        for (int i = 1; i < ReportData.size(); i++) {
                            String[] strings = ReportData.get(i);
                            if (strings[2].equalsIgnoreCase("Yes")) {
                                ScholarshipList1.add(new Scholarship(strings[0],Integer.parseInt(strings[1])));
                            }
                        }
                    }
                }
                catch (Exception a) {
                    System.out.println("Could not read Report" + a.getMessage());
                }

            MatchingReportGenerator generator = new MatchingReportGenerator(ScholarshipList1, Student1);
            String path = generator.writeToFile();

            try {
                    new GMailer().sendMail("Matching Report", "These are all the eligible scholarships for "+nameField.getText()+".", new File(path), emailField.getText());
                    System.out.println("Matching Report test passed");
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }
            }
        });

        JButton generateDataButton = new JButton("Generate Data");
        generateDataButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                        // Create an ArrayList to store dummy scholarships
                        scholarshipData = new ArrayList<>();

                        // Generate a specified number of random scholarships and add them to the ArrayList
                        generateRandomScholarships(scholarshipData, 3150);
            }
        });

        // Add buttons to the panel
        panel.add(disbursementButton);
        panel.add(scholarshipButton);
        panel.add(annualReportButton);
        panel.add(applicationButton);
        panel.add(matchingButton);
        panel.add(generateDataButton);

        // Set up the frame
        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

     /**
     * Generates a specified number of random scholarships and adds them to the provided ArrayList.
     *
     * @param scholarships     The ArrayList to which the generated scholarships will be added.
     * @param numberOfScholarships The number of scholarships to generate.
     */
    private static void generateRandomScholarships(ArrayList<Scholarship> scholarships, int numberOfScholarships) {
        for (int i = 0; i < numberOfScholarships; i++) {
            scholarships.add(generateRandomScholarship(scholarships));
        }

    }

    /**
     * Generates a single random scholarship.
     * @param scholarships The ArrayList of existing scholarships to avoid duplicate names.
     * @return A randomly generated Scholarship object.
     */
    private static Scholarship generateRandomScholarship(ArrayList<Scholarship> scholarships) {
        // You can customize the parameters of the generated scholarship here
        String scholarshipName = "Scholarship" + (scholarships.size() + 1);
        Random Rand = new Random();
        int payout = Rand.nextInt(2000) + 500; // Random payout between 500 and 2500
        String deadline = getRandomDate();
        ArrayList<String> majorChoices = new ArrayList<String>(List.of("Computer Science", "Software Engineering", "Electrical and Computer Engineering", "Computer Science and Engineering", "Systems Engineering", "Industrial Engineering"));
        ArrayList<String> InfoChoices = new ArrayList<String>(List.of("Transcript", "Essay", "Resume"));
        
        // Ensure disbursement date is greater than the deadline
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date deadlineDate = null;
        Date disbursementDate = null;
    
        try {
            deadlineDate = dateFormat.parse(deadline);
    
            // Generate a random disbursement date that is after the deadline
            do {
                String disbursementDateString = getRandomDate();
                disbursementDate = dateFormat.parse(disbursementDateString);
                if (disbursementDate.compareTo(deadlineDate) <= 0) {
                    // If disbursement date is not after the deadline, generate a new one
                    continue;
                }
    
                // If we reach here, the disbursement date is valid
                String customRequiredInfo = InfoChoices.get(Rand.nextInt(InfoChoices.size())); // You can customize this
                String preferedMajors = majorChoices.get(Rand.nextInt(majorChoices.size())); // You can customize this
    
                // Create and return a new Scholarship object
                return new Scholarship(scholarshipName, payout, deadline, disbursementDateString, customRequiredInfo, preferedMajors);
    
            } while (true);
    
        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing exceptions if needed
            return null;
        }
    }
    

    /**
     * Generates a random date within a specific range.
     *
     * @return A string representation of the random date.
     */
    private static String getRandomDate() {
        // Set the range of years for the random date
        int startYear = 2014;
        int endYear = 2024;

        // Generate a random year within the specified range
        int randomYear = startYear + new Random().nextInt(endYear - startYear + 1);

        // Generate a random month (1-12)
        int randomMonth = 1 + new Random().nextInt(12);

        // Generate a random day (1-28 for simplicity)
        int randomDay = 1 + new Random().nextInt(28);

        // Format the random date as "YYYY-MM-DD"
        return String.format("%04d-%02d-%02d", randomYear, randomMonth, randomDay);
    }

    /**
     * The main method to start the Scholarship Report Generator demo application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoMain();
            }
        });
    }



}
