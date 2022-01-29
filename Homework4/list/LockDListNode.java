package Homework4.list;

public class LockDListNode extends DListNode{

    // inherited:
    // public Object item;
    // protected DListNode prev;
    // protected DListNode next;
    boolean isLocked;


    LockDListNode(Object i, DListNode p, DListNode n) {
        super(i, p, n);
        isLocked = false;
    }
    
}
