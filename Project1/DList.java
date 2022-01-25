package Project1;
// I wrote this myself

public class DList{
    
    private DListNode sentinel = new DListNode(new pixel(-1, -1, -1), 0);
    public DListNode head = sentinel;
    public int size;
    
    public DList() {
        head.prev = sentinel;
        head.next = sentinel;
        size = 0;
    }

    public DList(pixel p, int runLength) {
        DListNode node = new DListNode(p, runLength);
        head.prev = node;
        head.next = node;
        size = 1;
    }

    public DList(pixel p1, int runLength1, pixel p2, int runLength2) {
        head = sentinel;
        DListNode node1 = new DListNode(p1, runLength1);
        DListNode node2 = new DListNode(p2, runLength2);
        head.next = node1;
        head.prev = node2;
        node1.next = node2;
        node1.prev = head;
        node2.next = head;
        node2.prev = node1;
        size = 2;
    }

    public void insertFront(pixel p, int runLength) {
        DListNode node = new DListNode(p, runLength);
        if (size == 0) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
        } else {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
        size++;
    }

    public void removeFront() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            head.prev = head;
            head.next = head;
        } else {
            head.next.next.prev = head;
            head.next = head.next.next;
        }
        size--;
    }

    public void insertEnd(pixel p, int runLength) {
        DListNode node = new DListNode(p, runLength);
        if (size == 0) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
        } else {
            head.prev.next = node;
            node.prev = head.prev;
            head.prev = node;
            node.next = head;
        }
        size++;
    }

    public void removeEnd() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            head.prev = head;
            head.next = head;
        } else {
            head.prev.prev.next = head;
            head.prev = head.prev.prev;
        }
        size--;
    }

    public String toString() {
        String results = "[   ";
        DListNode cursor = head;
        while (cursor.next != head) {
            cursor = cursor.next;
            results += cursor.toString();
        } 
        results += "]";
        return results;
    }
}
