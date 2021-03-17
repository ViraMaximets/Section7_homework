/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
 */

public class SinglyLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList sList = new SinglyLinkedList();
        sList.addNode(101);
        sList.addNode(202);
        sList.addNode(303);
        sList.addNode(04);
        sList.addNode(35);
        sList.addNode(06);

        sList.display();
        System.out.println("reverseDisplay:");
        sList.reverseDisplay();
    }

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head = null;
    public Node tail = null;

    public void addNode(int data) {
        Node nod1 = new Node(data);

        if (head == null) {
            head = nod1;
            tail = nod1;
        } else {
            tail.next = nod1;
            tail = nod1;
        }
    }

    public void display() {
        Node curr = head;

        while (curr != null) {
            System.out.println("Data: " + curr.data);
            curr = curr.next;
        }
    }

    Node newHead = null;

    public void reverseDisplay() {
        //reversing
        Node curr = head;
        Node nxt = null;
        Node last = null;

        while (curr != null) {
            if (curr.next == null) {
                newHead = curr;
                curr.next = last;
                break;
            }
            nxt = curr.next;
            curr.next = last;
            last = curr;
            curr = nxt;
        }

        //displaying
        curr = newHead;
        while (curr != null) {
            System.out.println("Data: " + curr.data);
            curr = curr.next;
        }
    }
}