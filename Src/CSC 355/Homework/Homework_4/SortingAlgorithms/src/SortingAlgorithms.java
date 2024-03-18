import java.util.*;

public class SortingAlgorithms{
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            
            // Print the array after each iteration
            printArray(arr);
        }
    }

    public static void insertionSort(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int[] temp = arr.clone();
            int key = arr[i];
            int j = i - 1;
            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            
            if(!arr.equals(temp)){
                printArray(arr);
            }
        }
    }

    public static void bubbleSort(int[] arr){
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // Print the array after each pass
                    printArray(arr);
                }
            }
            
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int middle = (left + right) / 2;

            // Sort first and second halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // Merge the sorted halves
            merge(arr, left, middle, right);

            // Print the array after each merge
            printArray(arr);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        /* Create temporary arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temporary arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[middle + 1 + j];

        /* Merge the temporary arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        String choice = "";
        boolean exit = false;
        
        while (!exit) {
            int[] arr = {3, 0, 4, 1, 5, 2, 9, 7, 8, 6};
            System.out.println("Original array:");
            printArray(arr);
            System.out.println("Select a sorting method or (Q) to quit.");
            System.out.println("Selection Sort(S) | Insertion Sort(I) | Bubble Sort(B) | Merge Sort(M)");
           
            choice = scnr.nextLine();

            switch (choice.toUpperCase()) {
                case "S":
                    selectionSort(arr);
                    break;
                case "I":
                    insertionSort(arr);
                    break;
            
                case "B":
                    bubbleSort(arr);
                    break;

                case "M":
                    mergeSort(arr);
                    break;
                case "Q":
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        scnr.close();
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}