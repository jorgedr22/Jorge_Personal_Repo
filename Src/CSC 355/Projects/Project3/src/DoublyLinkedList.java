public class DoublyLinkedList {
    private Node head;
    private Node tail;

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        }
    }

    public void deleteLast() {
        if (tail != null) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        }
    }

    public void deleteByValue(int data) {
        Node current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current != null) {
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev;
            }
        }
    }

    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void traverseForwardAndPrint() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data + "] <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void traverseBackwardAndPrint() {
        Node current = tail;
        while (current != null) {
            System.out.print("[" + current.data + "] <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    private static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
}
