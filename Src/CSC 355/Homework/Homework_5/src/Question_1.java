public class Question_1 {

    public static void main(String[] args)
    {
        // Homework array
        int arr[] = {4,8,1,9,0,2,3,7,6,3}; 
        int n = arr.length;
        
        int i, first, second, third;
  
        // There should be atleast three elements
        if (n < 3) {
            System.out.print(" Invalid Input ");
            return;
        }
 
        third = first = second = 0;
        for (i = 0; i < n; i++) {
            //If current element is greater than first
            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            }
 
            // If arr[i] is in between first and second then update second
            else if (arr[i] > second) {
                third = second;
                second = arr[i];
            }
 
            else if (arr[i] > third)
                third = arr[i];
        }
 
        System.out.println("Three largest elements are " + first + ", " + second + " and " + third + ".");
    }
}
