package Homework6.list;

public class SList {
    
    private int size;
    private SListNode head;

    // constructs an empty list
    public SList() {
        size = 0;
        head = null;
    }

    // check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the length of the list
    public int length() {
        return size;
    }

    // insert an item at the front of the list
    public void insertFront(Object obj) {
        head = new SListNode(obj, head);
        size++;
    } 

    // return the front node
    public SListNode front() {
        return head;
    }

    // return the last node
    public SListNode back() {
        SListNode node = head;
        while (node != null) {
            node = node.next;
        }
        return node;
    }

    // converts the list to a string
    public String toString() {
        String result = "[ ";
        SListNode cursor = head;
        Object obj;

        while (cursor != null) {
            obj = cursor.item;
            result = result + obj.toString() + ", ";
            cursor = cursor.next;
        }
        result += "]";
        return result;
    }

}
