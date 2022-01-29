/* DList.java */

package Homework4.list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
    head = newNode(Integer.MIN_VALUE, null, null);
    head.prev = head;
    head.next = head;
    size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
    DListNode node = newNode(item, head, head.next);
    head.next.prev = node;
    head.next = node;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    // Your solution here.
    DListNode node = newNode(item, head.prev, head);
    head.prev.next = node;
    head.prev = node;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
    if (this.isEmpty()) {
        return null;
    } else {
        return head.next;
    }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
    if (this.isEmpty()) {
        return null;
    } else {
        return head.prev;
    }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
    if (node == null || node.next == head) {
        return null;
    } else {
        return node.next;
    }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
    if (node == null || node.prev == head) {
        return null;
    } else {
        return node.prev;
    }
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    // Your solution here.
    if (node == null) {
        return;
    } else {
        DListNode newNode = newNode(item, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;
        size++;
    }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    // Your solution here.
    if (node == null) {
        return;
    } else {
        DListNode newNode = newNode(item, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
    if (node == null || size == 0) {
        return;
    } else {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
      System.out.println("Testing Homework4...");
      System.out.println("");        
      DList d = new DList();

    //   System.out.println("Testing DList() by creating an empty DList...");
    //   System.out.println("empty list:" + d);
    //   System.out.println("");

    //   System.out.println("Testing isEmpty()... should be true");
    //   System.out.println(d.isEmpty());
    //   System.out.println("");

    //   System.out.println("Testing front()... should be null");
    //   System.out.println(d.front());
    //   System.out.println("");

    //   System.out.println("Testing back()... should be null");
    //   System.out.println(d.back());
    //   System.out.println("");

      System.out.println("Testing insertFront...");
      d.insertFront(1);
      DListNode node1 = d.front();
      System.out.println("Should be [  1  ]:" + d);
      d.insertFront(2);
      DListNode node2 = d.front();
      System.out.println("Should be [  2  1  ]:" + d);
      d.insertBack(3);
      DListNode node3 = d.back();
      System.out.println("Should be [  2  1  3  ]:" + d);

      System.out.println("Testing front()... should be 2");
      System.out.println(d.front().item);
      System.out.println("");

      System.out.println("Testing back()... should be 3");
      System.out.println(d.back().item);
      System.out.println("");

      System.out.println("Testing next()... should be 1");
      System.out.println(d.next(node2).item);
      System.out.println("");

      System.out.println("Testing next()... should be null");
      System.out.println(d.next(null));
      System.out.println("");

      System.out.println("Testing next()... should be null");
      System.out.println(d.next(node3));
      System.out.println("");

      System.out.println("Testing prev()... should be 1");
      System.out.println(d.prev(node3).item);
      System.out.println("");

      System.out.println("Testing prev()... should be null");
      System.out.println(d.prev(null));
      System.out.println("");

      System.out.println("Testing prev()... should be 1");
      System.out.println(d.prev(node3).item);
      System.out.println("");

      System.out.println("Testing insertAfter... should be [  2  1  3  ]");
      d.insertAfter(Integer.valueOf(4), null);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing insertAfter... should be [  2  1  4  3  ]");
      d.insertAfter(Integer.valueOf(4), node1);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing insertBefore... should be [  2  1  4  3  ]");
      d.insertAfter(Integer.valueOf(5), null);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing insertBefore... should be [  2  1  4  5  3  ]");
      d.insertBefore(Integer.valueOf(5), node3);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing remove()...should be [  2  1  4  5  3  ]");
      d.remove(null);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing remove()...should be [  2  4  5  3  ]");
      d.remove(node1);
      System.out.println(d);
      System.out.println("");

      System.out.println("Testing isEmpty...should be false");
      System.out.println(d.isEmpty());
      System.out.println("");

      System.out.println("Testing length...should be 4");
      System.out.println(d.length());
      System.out.println("");

      
  }
}
