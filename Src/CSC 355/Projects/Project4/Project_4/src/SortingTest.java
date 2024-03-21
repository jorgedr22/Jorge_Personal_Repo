// SortingTest.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingTest {
    public static void main(String[] args) {
        String[] files = {"array1.txt", "array2.txt", "array3.txt", "array4.txt", "array5.txt"};

        for (String file : files) {
            System.out.println("Sorting " + file + ":");
            int[] array1 = readArrayFromFile(file);
            int[] array2 = readArrayFromFile(file);
            System.out.println("Original Array: " + Arrays.toString(array1));

            System.out.println("Sorted using Shell Sort:");
            Shellsort.sort(array1);
            System.out.println(Arrays.toString(array1));
            System.out.println("Number of passes (Shellsort): " + Shellsort.passes);

            System.out.println("Sorted using Merge Sort:");
            MergeSort.mergeSort(array2);
            System.out.println(Arrays.toString(array2));
            System.out.println("Number of passes (Merge Sort): " + MergeSort.passes);


            System.out.println();
        }
    }

    private static int[] readArrayFromFile(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String number : numbers) {
                    if (!number.isEmpty()) {
                        list.add(Integer.parseInt(number));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert list to int array
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
