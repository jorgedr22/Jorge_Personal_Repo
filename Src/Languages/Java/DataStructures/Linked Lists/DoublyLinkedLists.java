public class DoublyLinkedLists {    
    //A node class for doubly linked list
    class Node{  
        int data;  
        Node previous;  
        Node next;  
   
        public Node(int data) {  
            this.data = data;  
        }  
    }  

    //Initially, heade and tail is set to null
    Node head, tail = null;  
   
    //print all the nodes of doubly linked list  
    public void printNodes() {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");  
            return;  
        }  
        System.out.println("Nodes of doubly linked list: ");  
        while(current != null) {  
            //Print each node and then go to next.  
            System.out.print(current.data + " ");  
            current = current.next;  
        }  
    }
    
    // Traversing from head to the end of the list 
    public void traverseForward(){ 
        Node current = head; 
        while (current != null) { 
            System.out.print(current.data + " "); 
            current = current.next; 
        } 
    }

    // Traversing from tail to the head 
    public void traverseBackward(){ 
        Node current = tail; 
        while (current != null) { 
            System.out.print(current.data + " "); 
            current = current.previous; 
        } 
    }
    
    // Adding a new node at the HEAD of the list
    public void insertAtBeginning(int data){ 
        Node temp = new Node(data); 
        //if list is empty, head and tail points to newNode  
        if(head == null) {  
            head = tail = temp;  
            //head's previous will be null  
            head.previous = null;  
            //tail's next will be null  
            tail.next = null;  
        }  
        else {  
            //add new Node to the end of list. tail->next set to newNode  
            tail.next = temp;  
            //temp->previous set to tail  
            temp.previous = tail;  
            //temp becomes new tail  
            tail = temp;  
            //tail's next point to null  
            tail.next = null;  
        } 
    }
    
    // Adding a new node at a certain location of the list
    public void insertAtPosition(int data, int position) { 
        Node temp = new Node(data); 
        if (position == 1) { 
            insertAtBeginning(data); 
        } 
        else { 
            Node current = head; 
            int currPosition = 1; 
            while (current != null && currPosition < position) { 
                current = current.next; 
                currPosition++; 
            } 
            if (current == null) { 
                insertAtEnd(data); 
            } 
            else { 
                temp.next = current; 
                temp.previous = current.previous; 
                current.previous.next = temp; 
                current.previous = temp; 
            } 
        } 
    }

    // Adding new node at the TAIL of the list
    public void insertAtEnd(int data) { 
        Node temp = new Node(data); 
        if (tail == null) { 
            head = temp; 
            tail = temp; 
        } 
        else { 
            tail.next = temp; 
            temp.previous = tail; 
            tail = temp; 
        } 
    }

    // Removing HEAD and reassigning a new HEAD
    public void deleteAtBeginning() { 
        if (head == null) { 
            return; 
        } 
    
        if (head == tail) { 
            head = null; 
            tail = null; 
            return; 
        } 
    
        Node temp = head; 
        head = head.next; 
        head.previous = null; 
        temp.next = null; 
    }

    // Removing a node from the list
    public void delete(int pos) { 
        if (head == null) { 
            return; 
        } 
    
        if (pos == 1) { 
            deleteAtBeginning(); 
            return; 
        } 
    
        Node current = head; 
        int count = 1; 
    
        while (current != null && count != pos) { 
            current = current.next; 
            count++; 
        } 
    
        if (current == null) { 
            System.out.println("Position wrong"); 
            return; 
        } 
    
        if (current == tail) { 
            deleteAtEnd(); 
            return; 
        } 
    
        current.previous.next = current.next; 
        current.next.previous = current.previous; 
        current.previous = null; 
        current.next = null; 
    } 

    // Removing TAIL and reassigning a new TAIL
    public void deleteAtEnd() { 
        if (tail == null) { 
            return; 
        } 
    
        if (head == tail) { 
            head = null; 
            tail = null; 
            return; 
        } 
    
        Node temp = tail; 
        tail = tail.previous; 
        tail.next = null; 
        temp.previous = null; 
    }

    public static void main(String[] args) {  
        //create a DoublyLinkedList object
        DoublyLinkedLists list = new DoublyLinkedLists();  
        //Add nodes to the list  
        list.insertAtBeginning(10);  
        list.insertAtBeginning(20);  
        list.insertAtBeginning(30);  
        list.insertAtBeginning(40);  
        list.insertAtBeginning(50);  
   
        //print the nodes of DoublyLinkedList  
        list.printNodes();  

        System.out.println("\nTraverse Forward:");
        
        
        list.traverseForward();

        System.out.println("\nTraverse Backward:");

        list.traverseBackward();

        list.insertAtEnd(69);
        System.out.println("\nAdded new data at the end.");
        list.printNodes();
    }  
}