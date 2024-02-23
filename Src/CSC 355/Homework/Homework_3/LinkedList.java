package Homework.Homework_3;

/*
 * Question 1a 
 * Question 1b
 */

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class LinkedList {

    public static void main(String[] args) {
        // Example list: 3 → 4 → 1 → 0 → 2 → 9
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(9);

        printList(head);
        System.out.println();
        ListNode[] result = splitList(head);

        System.out.print(head.val+" → ");
        printList(result[0]); // L1 = 3 → 1 → 0 → 2
        System.out.println();
        printList(result[1]); // L2 = 4 → 9
        System.out.println();
    }

    public static ListNode[] splitList(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, null};
        }

        int pivot = head.val;

        ListNode[] result = new ListNode[]{null, null};

        ListNode current = head.next;
        while (current != null) {
            ListNode next = current.next;
            if (current.val <= pivot) {
                current.next = result[0];
                result[0] = current;
            } else {
                current.next = result[1];
                result[1] = current;
            }
            current = next;
        }

        return result;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            }
            else{
                System.out.print(head.val + " → ");   
            }
            head = head.next;
        }
    }
}
