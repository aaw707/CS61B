package Homework5;

/* Set.java */

import Homework5.list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/
  protected List elements;

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    elements = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return elements.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    //set cursor at the front of the list
    ListNode cursor = elements.front();
    Comparable cursor_item;

    try {

        while (cursor.isValidNode()) {
            cursor_item = (Comparable) cursor.item();

            if (cursor_item.compareTo(c) > 0) {
                // insert c before cursor
                cursor.insertBefore(c); 
                return;
      
            } else if (cursor_item.compareTo(c) < 0) {
                cursor = cursor.next(); 
            
            } else {
                // cursor_item = c. duplicate
                // no insertion performed
                return;
            }
        }
        // no more item after the cursor
        elements.insertBack(c);
        return;

    } catch (InvalidNodeException e) {
        e.printStackTrace();
    }
  }

    
  

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    ListNode this_cursor = this.elements.front();
    ListNode s_cursor = s.elements.front();
    Comparable this_cursor_item;
    Comparable s_cursor_item;

    try {

        while (s_cursor.isValidNode()) {
            s_cursor_item = (Comparable) s_cursor.item();

            if (this_cursor.isValidNode()) {
                this_cursor_item = (Comparable) this_cursor.item();

                // do union
                if (this_cursor_item.compareTo(s_cursor_item) < 0) {
                    // cursor on this set has the smaller value
                    // "insert" this cursor node to this list
                    this_cursor = this_cursor.next();
        
                } else if (this_cursor_item.compareTo(s_cursor_item) > 0) {
                    // s set has the smaller value
                    // insert s cursor node to this list
                    this_cursor.insertBefore(s_cursor_item);
                    s_cursor = s_cursor.next();
                      
                } else {
                    // duplicates. take the item from this set
                    this_cursor = this_cursor.next();
                    s_cursor = s_cursor.next();
                }

            } else {
                // no more item in this set
                // insert everything remaining in s set to the end of this set
                while (s_cursor.isValidNode()) {
                    s_cursor_item = (Comparable) s_cursor.item();
                    this.elements.insertBack(s_cursor_item);
                    s_cursor = s_cursor.next(); // this line should not throw an error. s_cursor has been validated
                }
                // remaining items in s list have been unioned into this list
                // end function
                return;
            }
        }
            
        // no more item in s set
        // no more item to be unioned
        // end function
        return;
    
    } catch (InvalidNodeException e) {
        e.printStackTrace();
    }
  }




  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    ListNode this_cursor = this.elements.front();
    ListNode s_cursor = s.elements.front();
    Comparable this_cursor_item;
    Comparable s_cursor_item;

    try {
        
        while (true) {
                
            if (this_cursor.isValidNode()) {
                // get the item in this cursor node
                this_cursor_item = (Comparable) this_cursor.item();
                // System.out.println("this_cursor_item:" + this_cursor_item);

                if (s_cursor.isValidNode()) {
                    // get the item in s cursor node
                    s_cursor_item = (Comparable) s_cursor.item();
                    // System.out.println("s_cursor_item:" + s_cursor_item);  

                    // do intersect
                    if (this_cursor_item.compareTo(s_cursor_item) < 0) {
                        // this cursor element won't appear in s set
                        // remove it from this list

                        // move the cursor to the next
                        this_cursor = this_cursor.next();
                        if (this_cursor.isValidNode()) {
                            // remove the previously compared node as it won't be an intersect
                            this_cursor.prev().remove();
                        } else {
                            // the node to be removed is the last element in this set
                            this.elements.back().remove();
                        }
            
                    } else if (this_cursor_item.compareTo(s_cursor_item) > 0){
                        // move on to the next element in s set
                        s_cursor = s_cursor.next();
            
                    } else {
                        // this item is an intersect
                        this_cursor = this_cursor.next();
                        s_cursor = s_cursor.next();
                    }

                } else {
                    // s set has ended
                    // no more possible intersects
                    // remove all remaining elements in this set
                    while (this_cursor.next().isValidNode()) { // this_cursor is already validated
                        this_cursor = this_cursor.next();
                        this_cursor.prev().remove();
                    }
                    // this cursor is the last element in this list
                    // remove it
                    this_cursor.remove();
                    return;
                }
            } else {
                // this set has ended. no more possible intersects
                // function ends
                return;
            }
        }
    } catch (InvalidNodeException e) {
        e.printStackTrace();
    }
  }  

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    ListNode cursor = elements.front();
    String results = "[  ";
    while (true) {
        try {
            results += cursor.item().toString();
            results += "  ";
            cursor = cursor.next();
        } catch (InvalidNodeException e) {
            break;
        }
    }
    results += "]";
    return results;
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(Integer.valueOf(3));
    s.insert(Integer.valueOf(4));
    s.insert(Integer.valueOf(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(Integer.valueOf(4));
    s2.insert(Integer.valueOf(5));
    s2.insert(Integer.valueOf(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(Integer.valueOf(5));
    s3.insert(Integer.valueOf(3));
    s3.insert(Integer.valueOf(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.

    Set s4 = new Set();
    s4.insert(Integer.valueOf(1));
    s4.insert(Integer.valueOf(3));
    s4.insert(Integer.valueOf(5));
    s4.insert(Integer.valueOf(7));
    s4.insert(Integer.valueOf(9));
    s4.insert(Integer.valueOf(11));
    System.out.println("Set s4 = " + s4);

    Set s5 = new Set();
    s5.insert(Integer.valueOf(0));
    s5.insert(Integer.valueOf(1));
    s5.insert(Integer.valueOf(2));
    s5.insert(Integer.valueOf(3));
    s5.insert(Integer.valueOf(4));
    s5.insert(Integer.valueOf(5));
    s5.insert(Integer.valueOf(6));
    s5.insert(Integer.valueOf(15));
    System.out.println("Set s5 = " + s5);

    s4.union(s5);
    System.out.println("After s4.unions(s5), s4 = " + s4);


  }
}
