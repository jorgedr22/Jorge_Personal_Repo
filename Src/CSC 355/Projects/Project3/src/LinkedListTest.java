import java.util.Scanner;

public class LinkedListTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SimplyLinkedList singlyList = new SimplyLinkedList();
        DoublyLinkedList doublyList = new DoublyLinkedList();

        while (true) {
            System.out.println("Options:");
            System.out.println("1) Insert (Ask number of elements)");
            System.out.println("2) Delete");
            System.out.println("   1. First element in the list");
            System.out.println("   2. Last element in the list");
            System.out.println("   3. By element");
            System.out.println("3) Search");
            System.out.println("   1. By element");
            System.out.println("4) Traversal & Print");
            System.out.println("5) Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter the number of elements to insert: ");
                    int numElements = scanner.nextInt();
                    for (int i = 0; i < numElements; i++) {
                        System.out.print("Enter element " + (i + 1) + ": ");
                        int element = scanner.nextInt();
                        singlyList.insert(element);
                        doublyList.insert(element);
                    }
                    break;

                case 2:
                    System.out.println("Delete options:");
                    System.out.println("   1. First element in the list");
                    System.out.println("   2. Last element in the list");
                    System.out.println("   3. By element");
                    System.out.print("Choose an option: ");
                    int deleteOption = scanner.nextInt();

                    switch (deleteOption) {
                        case 1:
                            singlyList.deleteFirst();
                            doublyList.deleteFirst();
                            break;
                        case 2:
                            singlyList.deleteLast();
                            doublyList.deleteLast();
                            break;
                        case 3:
                            System.out.print("Enter the element to delete: ");
                            int elementToDelete = scanner.nextInt();
                            singlyList.deleteByValue(elementToDelete);
                            doublyList.deleteByValue(elementToDelete);
                            break;
                        default:
                            System.out.println("Invalid option for delete.");
                    }
                    break;

                case 3:
                    System.out.println("Search options:");
                    System.out.println("   1. By element");
                    System.out.print("Choose an option: ");
                    int searchOption = scanner.nextInt();

                    switch (searchOption) {
                        case 1:
                            System.out.print("Enter the element to search: ");
                            int elementToSearch = scanner.nextInt();
                            long startTimeSingly = System.nanoTime();
                            boolean foundSingly = singlyList.search(elementToSearch);
                            long endTimeSingly = System.nanoTime();
                            long elapsedTimeSingly = endTimeSingly - startTimeSingly;

                            long startTimeDoubly = System.nanoTime();
                            boolean foundDoubly = doublyList.search(elementToSearch);
                            long endTimeDoubly = System.nanoTime();
                            long elapsedTimeDoubly = endTimeDoubly - startTimeDoubly;

                            System.out.println("Found in singly linked list: " + foundSingly);
                            System.out.println("Time taken (Singly): " + elapsedTimeSingly + " nanoseconds");
                            System.out.println("Found in doubly linked list: " + foundDoubly);
                            System.out.println("Time taken (Doubly): " + elapsedTimeDoubly + " nanoseconds");
                            break;
                        default:
                            System.out.println("Invalid option for search.");
                    }
                    break;

                case 4:
                    System.out.println("Traversal & Print options:");
                    System.out.println("   1. Singly Linked List");
                    System.out.println("   2. Doubly Linked List (Forward)");
                    System.out.println("   3. Doubly Linked List (Backward)");
                    System.out.print("Choose an option: ");
                    int printOption = scanner.nextInt();

                    switch (printOption) {
                        case 1:
                            System.out.println("Singly Linked List:");
                            singlyList.traverseAndPrint();
                            break;
                        case 2:
                            System.out.println("Doubly Linked List (Forward):");
                            doublyList.traverseForwardAndPrint();
                            break;
                        case 3:
                            System.out.println("Doubly Linked List (Backward):");
                            doublyList.traverseBackwardAndPrint();
                            break;
                        default:
                            System.out.println("Invalid option for print.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
