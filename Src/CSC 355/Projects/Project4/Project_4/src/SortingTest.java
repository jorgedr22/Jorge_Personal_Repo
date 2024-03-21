// SortingTest.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SortingTest {
    static String userDirectory = System.getProperty("user.dir"); // opens the correct directory
    public static void main(String[] args) {
        String[] files = {"array1.txt", "array2.txt", "array3.txt", "array4.txt", "array5.txt"}; // name of array files
        String[] grid_files = {"testGrid1.txt","testGrid2.txt","testGrid3.txt","testGrid4.txt","testGrid5.txt"}; // name of grid files
        Scanner scnr = new Scanner(System.in);
        boolean quit = false; // initilzed to always run 

        System.out.println("What are you going to be testing?");
        System.out.println("For Array testing [1] | For Grid testing [2] | For Quiting [3]");
        int sorting = scnr.nextInt(); // determines the sorting type

        while (!quit) { // runs program until user quits
            switch (sorting) {
                case 1:
                    for (String file : files) {
                        System.out.println("Sorting " + file + ": --------------------------------");
                        int[] array1 = readArrayFromFile(file);
                        int[] array2 = readArrayFromFile(file);
                        System.out.println("Original Array: " + Arrays.toString(array1));
            
                        System.out.println("Sorted using Shell Sort: --------------------------------");
                        Shellsort.sort(array1);
                        System.out.println(Arrays.toString(array1));
                        System.out.println("Number of passes (Shellsort): " + Shellsort.passes + " --------------------------------");
            
                        System.out.println("Sorted using Merge Sort: --------------------------------");
                        MergeSort.mergeSort(array2);
                        System.out.println(Arrays.toString(array2));
                        System.out.println("Number of passes (Merge Sort): " + MergeSort.passes+" --------------------------------");
                        System.out.println();

                    }
                    System.out.println("What are you going to be testing?");
                    System.out.println("For Array testing [1] | For Grid testing [2] | For Quiting [3]");
                    sorting = scnr.nextInt();
                    break;
                case 2:
                    for (String grid : grid_files) {
                        System.out.println("Sorting " + grid + ":--------------------------------");
                        int[][] Grid = readGridFromFile(grid);
                        if (Grid != null) {
                            System.out.println("Grid read from file:--------------------------------");
                            for (int[] row : Grid) {
                                for (int cell : row) {
                                    System.out.print(cell + " ");
                                }
                                System.out.println();
                            }
                        }
                        System.out.println("--------------------------------");
                        Grid = SortGrid.Sort(Grid);
                        // Print the sorted grid
                        for (int[] row : Grid) {
                            for (int num : row) {
                                System.out.print(num + " ");
                            }
                            System.out.println();
                        }
                    }
                    System.out.println("What are you going to be testing?");
                    System.out.println("For Array testing [1] | For Grid testing [2] | For Quiting [3]");
                    sorting = scnr.nextInt();
                    break;
                case 3:
                    System.out.println("Thank you for testing");
                    quit = true; // exits program
                    break;
                default:
                    break;
            }
        }
        
        scnr.close();
    }
    private static int[] readArrayFromFile(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(userDirectory + "/TestFiles/" + filename))) {
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
    private static int[][] readGridFromFile(String gridpath) {
        int[][] grid = null;
        try (BufferedReader br = new BufferedReader(new FileReader(userDirectory + "/TestFiles/" + gridpath))) {
            String line = br.readLine();
            if (line != null) {
                int size = Integer.parseInt(line.trim()); // size of square grid
                grid = new int[size][size];

                for (int i = 0; i < size; i++) {
                    line = br.readLine();
                    if (line != null) {
                        String[] values = line.trim().split("\\s+");
                        for (int j = 0; j < size; j++) {
                            grid[i][j] = Integer.parseInt(values[j]);
                        }
                    } else {
                        throw new IOException("File is not formatted properly.");
                    }
                }
            } else {
                throw new IOException("File is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grid; // returns square grid grid
    }
    }