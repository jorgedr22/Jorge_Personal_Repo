import java.util.*;;

public class LinkedLists {
    public static void main(String[] args) {
        
        LinkedList<String> linkedList = new LinkedList<String>();

        // Adds nodes to the linked list and makes new object HEAD of list
        linkedList.push("A");
        linkedList.push("B");
        linkedList.push("C");
        linkedList.push("D");
        linkedList.push("F");

        // Similar to Queue
        // linkedList.offer("A");
        // linkedList.offer("B");
        // linkedList.offer("C");
        // linkedList.offer("D");
        // linkedList.offer("F");


        System.out.println(linkedList);

        // Inserts a node at index 4
        linkedList.add(4,"E");
        System.out.println(linkedList);

        // Removes certain node from list
        linkedList.remove("E");
        System.out.println(linkedList);

        // Shows the first object in the list
        System.out.println(linkedList.peekFirst());

        // Shows the last object in the list
        System.out.println(linkedList.peekLast());

        //Search for an object in the list and returns the index
        System.out.println(linkedList.indexOf("C"));

        //Add directly to HEAD or TAIL of list
        linkedList.addFirst("0");
        linkedList.addLast("1");
        System.out.println(linkedList);

        // Removing and storing first or last nodes
        String first = linkedList.removeFirst();
        String last = linkedList.removeLast();

        System.out.println("The first node was "+ first+"\n"+"The last node was "+last);
        System.out.println(linkedList);
    }
}
