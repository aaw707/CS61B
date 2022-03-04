package Homework6.list;

public class SListNode {

    Object item;
    SListNode next;

    public SListNode() {
        item = null;
        next = null;
    }

    public SListNode(Object obj) {
        item = obj;
        next = null;
    }

    public SListNode(Object obj, SListNode next) {
        item = obj;
        this.next = next;
    }

    // return the item
    public Object item(){
        return item;
    }

    // return next
    public SListNode next() {
        return next;
    }

    // set item
    public void setItem(Object obj) {
        item = obj;
    }

    // set next
    public void setNext(SListNode node) {
        next = node;
    }
    
}
