public class SimplyLinkedList {
    private Node head;

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    public void deleteLast() {
        if (head == null || head.next == null) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
    }

    public void deleteByValue(int data) {
        if (head != null) {
            if (head.data == data) {
                head = head.next;
                return;
            }

            Node current = head;
            while (current.next != null && current.next.data != data) {
                current = current.next;
            }

            if (current.next != null) {
                current.next = current.next.next;
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

    public void traverseAndPrint() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
