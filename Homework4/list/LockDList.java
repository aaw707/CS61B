package Homework4.list;

/**
* a list in which any node can be "locked." 
* A locked node can never be removed from its list. 
* Any attempt to remove a locked node has no effect (not even an error message). 
 */

public class LockDList extends DList {

    public LockDList() {
        super();
    }

    public void lockNode(DListNode node) {
        ((LockDListNode) node).isLocked = true;
    }

    protected DListNode newNode(Object item, DListNode prev, DListNode next){
        DListNode newNode = new LockDListNode(item, prev, next);
        return newNode;
    }

    public void remove(DListNode node) {
        if (((LockDListNode) node).isLocked == true) {
            return;
        } else if (node == null || size == 0) {
            return;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }
}

    