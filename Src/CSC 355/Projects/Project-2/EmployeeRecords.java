import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class EmployeeRecords {

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      ArrayList<EmployeeData> emplList = new ArrayList<EmployeeData>(); // Stores all employee data                                           // Stores info for one employee
      String userCommand;                                               // User defined add/print/quit command
      String emplFirstName;                                             // User defined employee first name
      String emplLastName;                                              // User defined employee last name
      Integer emplID;                                                   // User defined employee ID
      Integer deptNum;                                                  // User defined employee Dept
      int i;                                                            // Loop counter

      do {
         // Prompt user for input
         System.out.println("Enter command ('a' to add new employee, 'p' to print all employees, 'q' to quit): ");
         userCommand = scnr.next();

         // Add new employee entry
         if (userCommand.equals("a")) {
            System.out.println("First Name: ");
            emplFirstName = scnr.next();
            System.out.println("Last Name: ");
            emplLastName = scnr.next();
            System.out.println("ID: ");
            emplID = scnr.nextInt();
            System.out.println("Department Number: ");
            deptNum = scnr.nextInt();
            EmployeeData emplData = new EmployeeData(emplFirstName, emplLastName, emplID, deptNum);
            emplList.add(emplData);
         }
         // Print all entries
         else if (userCommand.equals("p")) {

            // Sort employees by department number first
            // and name second
            Collections.sort(emplList);

            System.out.println("");
            System.out.println("Employees: ");
            // Access employee records
            for (i = 0; i < emplList.size(); ++i) {
               System.out.println(emplList.get(i).toString());
            }
            System.out.println("");
         }
      } while (!userCommand.equals("q"));
   }
}
