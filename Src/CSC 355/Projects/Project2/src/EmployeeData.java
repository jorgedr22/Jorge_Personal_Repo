
public class EmployeeData implements Comparable<EmployeeData> {
   private String firstName; // First Name
   private String lastName;  // Last Name
   private Integer emplID;   // Employee ID
   private Integer deptNum;  // Department Number

   EmployeeData(String firstName, String lastName, Integer emplID, Integer deptNum) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.emplID = emplID;
      this.deptNum = deptNum;
   }

   @Override
   public int compareTo(EmployeeData otherEmpl) {
   // Compare based on department number first
   int comparisonVal = deptNum.compareTo(otherEmpl.deptNum);
   
   // If in the same department, compare based on employee ID
   if (comparisonVal == 0) {
      return emplID.compareTo(otherEmpl.emplID);
   }

   return comparisonVal;
   }

   @Override
   public String toString() {
      return lastName + " " + firstName + 
             "\tID: " + emplID + 
             "\t\tDept. #: " + deptNum;
   }
}
