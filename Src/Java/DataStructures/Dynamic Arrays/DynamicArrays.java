
class DynamicArray {

    // Member variables of this class
    // Private access modifier
    private int arr[];
    private int count;
 
    // Note they can only be called through function
    
    // Default Method with array of size 5
    public DynamicArray() { 
        arr = new int[5]; 
    }

    // Method 1
    // Inside helper class
    // to compute length of an array
    public DynamicArray(int length) { 
        arr = new int[length]; 
    }
 
    // Method 2
    // To print array
    public void printArray(){
 
        // Iterating over array using for loop
        for (int i = 0; i < arr.length; i++) {
 
            // Print the elements of an array
            System.out.print(arr[i] + " ");
        }
    }
 
    // Method 3
    // Insert element at TAIL of array
    public void insert(int element){
 
        if (arr.length == count) {
 
            // Creating a new array that dynamic changes size
            // as all spaces of arr get occupied
            int newArr[] = new int[count+1];
 
            // Iterating over new array using for loop
            for (int i = 0; i < count; i++) {
                newArr[i] = arr[i];
            }
 
            // Assigning new array to original array
            // created above
            arr = newArr;
        }
        arr[count++] = element;
    }

    // Method 4
    // Insert element at index of array
    public void insertAt(int index, int element){        
        // Creating a new array that dynamic changes size
        // as all spaces of arr get occupied
        int newArr[] = new int[arr.length+1];

        // Iterating over new array using for loop
        for (int i = 0; i < arr.length+1; i++) {
            if(i == index){
                newArr[i] = element;
            }
            else if(i<index){
                newArr[i] = arr[i];                
            }
            else{
                newArr[i] = arr[--i];
                i++;
            }
        }
        // Assigning new array to original array
        // created above
        arr = newArr;
        count++;
    }

    //Method 5
    // Checks if array is empty
    public boolean isEmpty(){
        for (int i : arr) {
            if(arr[i]>0){
                return false;
            }
        }
        return true;
    }

    //Method 6
    // Delete the HEAD of the array
    public void deleteFirst(){

        int newArr[] = new int[arr.length-1];

        for (int i = 0; i < arr.length-1; i++) {
            newArr[i] = arr[++i];
            i--;
        }
        arr = newArr;
    }

    //Method 7
    // Delete the TAIL of the array
    public void deleteLast(){

        int newArr[] = new int[arr.length-1];

        for (int i = 0; i < arr.length-1; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    //Method 8
    // Delete any element in the array
    public void deleteAt(int index){
        int newArr[] = new int[arr.length-1];

        for (int i = 0; i < arr.length; i++) {
            if(i<index){
                newArr[i] = arr[i];
            }
            else if(i > index){
                --i;
                newArr[i] = arr[++i];
            }
        }
        arr = newArr;
    }
    
    // Main driver method
    public static void main(String[] args)
    {
 
        // Creating object of Array(user-defined) class
        DynamicArray numbers = new DynamicArray();
 
        // Adding elements more than size specified above
        // to the array to illustrate dynamic nature
        // using the insert() method

        // Custom input elements
        numbers.insert(1);
        numbers.insert(2);
        numbers.insert(3);
        numbers.insert(4);
        numbers.insert(5);
        numbers.insert(6);
        numbers.insert(7);
        numbers.insert(8);
        numbers.insert(9);
        numbers.insert(10);
        numbers.insert(11);
        numbers.insert(12);
        numbers.insert(13);
        numbers.insert(14);

        // Calling the printArray() method to print
        // new array been dynamically created
        numbers.printArray();
        System.out.println("");

    }
}
