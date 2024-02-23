package Homework.Homework_3;

/*
 * Question 2
 */
public class MergeArrays {
    public static int SeperateArray(int[] arr){

        for (int i = 0;i<arr.length-2;i++) {
                if(arr[i] > arr[i+1]){
                    return i;
                }
        }
        return arr.length-1;
    }

    public static void SortSubArrays(int[] arr){

    int sub_array_end_index = SeperateArray(arr);

    int[] sub_arr1 = new int[sub_array_end_index+1];
    int[] sub_arr2 = new int[arr.length - sub_array_end_index-1];

    for (int i = 0; i < sub_array_end_index+1; i++) {
        sub_arr1[i] = arr[i];
    }

    for (int i = 0; i < arr.length-sub_array_end_index-1; i++) {
        sub_arr2[i] = arr[sub_array_end_index+1+i];
    }

    int[] SortedSubArrays = new int[arr.length];

    int i=0;
    int j=0;

    for (i = 0; i < SortedSubArrays.length-1; i++) {
        try{
        if (sub_arr1[i] < sub_arr2[j]) {
            SortedSubArrays[i] = sub_arr1[i];
        }
        else if(sub_arr1[i] > sub_arr2[j]){
            SortedSubArrays[i] = sub_arr2[j];
            j = j + 1;
            i = i - 1;
        }
        else if (sub_arr1[i] == sub_arr2[j]){
            SortedSubArrays[i] = sub_arr1[i];
        }
        }
        catch(Exception e){
            SortedSubArrays[j + i-1] = arr[j + i];
        }
        System.out.print(SortedSubArrays[i]+", ");
    }

    System.out.println("The sorted Sub-Arrays is:");
    System.out.print("[");
    for (int k = 0;k<arr.length;k++) {
        if(k == SortedSubArrays.length-2){
            System.out.println(SortedSubArrays[k]+"]");
        }
        else if (k < SortedSubArrays.length-2){
            System.out.print(SortedSubArrays[k] + ", ");
        }
    }
    }
    public static void main(String[] args) {
        int[] arr = {0, 2, 5, 6, 2, 6, 6, 7, 8, 9}; // from example
        //int[] arrTest = {1,3,4,5,6,7,8,1,1,2,3,4,9};
        SortSubArrays(arr);
        //SortSubArrays(arrTest);
    }
}

