public class SortGrid {
    public static int[][] Sort(int[][] grid) {
        int n = grid.length;
        
        // Flatten the grid into a single array for sorting
        int[] flattenedGrid = new int[n * n];
        int index = 0;
        for (int[] row : grid) {
            for (int num : row) {
                flattenedGrid[index++] = num;
            }
        }
        
        // Sort the flattened grid
        bubbleSort(flattenedGrid);
        
        // Reconstruct the sorted grid
        int[][] sortedGrid = new int[n][n];
        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sortedGrid[i][j] = flattenedGrid[index++];
            }
        }
        
        return sortedGrid;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
