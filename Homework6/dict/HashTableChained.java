/* HashTableChained.java */

package Homework6.dict;
import Homework6.list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  // number of buckets
  protected int bucketNum;
  // number of entries
  protected int size;
  // array of buckets
  protected SList[] hashtable;
  // number of collisions
  protected int collisions;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // set a bucket number, with a load factor between 0.5 and 1, preferrable a prime
    int minBuckets = (int) Math.ceil(sizeEstimate * 0.5);
    int maxBuckets = sizeEstimate;
    boolean primeFound = false;
    // in the range set by load factor, try to find a prime
    for (int i = minBuckets; i <= maxBuckets; i++) {
        if (isPrime(i)) {
            // when prime is found, set it as the bucket number
            bucketNum = i;
            primeFound = true;
            break;
        }
    }
    // if no prime is found, set bucket number with a load factor around 0.5
    if (!primeFound) {
        bucketNum = sizeEstimate;
    }
    // set the params
    size = 0;
    collisions = 0;
    // create an array of SLists as the hash table
    hashtable = new SList[bucketNum];
    // initialize each SList
    for (int i = 0; i < bucketNum; i++) {
        hashtable[i] = new SList();
    } 
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
      // default size: a prime in the neighborhood of 100
      final int DEFAULT_SIZE = 101;
      // set the params
      bucketNum = DEFAULT_SIZE;
      size = 0;
      collisions = 0;
      hashtable = new SList[bucketNum];
      // initialize each SList
      for (int i = 0; i < bucketNum; i++) {
        hashtable[i] = new SList();
      }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    int num = code % bucketNum;
    if (num < 0) {
        num += bucketNum;
    }
    return num;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
      return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
      // hash the key 
      int hashCode = key.hashCode();
      // calculate the bucket to store the entry
      int bucket = compFunction(hashCode);
      // System.out.println("bucketNum:" + bucketNum + ", bucket:" + bucket);
      // create and assign the entry
      Entry e = new Entry();
      e.key = key;
      e.value = value;
      // System.out.println(e);
      // detect collision
      SList list = hashtable[bucket];
      if (list.front() != null) {
        collisions++;
      }
      //insert the entry
      list.insertFront(e);
      // increase size
      size++;
      // return the entry
      return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int hashCode = key.hashCode();
    int bucket = compFunction(hashCode);

    SList list = hashtable[bucket];
    // System.out.println("hashCode:" + hashCode);
    // System.out.println("bucket:" + bucket);
    // System.out.println("list:" + list);
    
    // list is empty
    if (list.length() == 0) {
        return null;
    }

    SListNode node = list.front();
    
    while (node != null) {
        //System.out.println("node:" + node.item());
        if (((Entry)node.item()).key() == key) {
            return (Entry) node.item();
        }
        node = node.next();
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    int hashCode = key.hashCode();
    int bucket = compFunction(hashCode);

    SList list = hashtable[bucket];
    SListNode node = list.front();

    if (node == null) {
        // empty list
        return null;

    } else {
        // remove the node
        while (node.next() != null) {
            if (((Entry) node.next().item()).key() == key) {
                node.setNext(node.next().next());
                // decrease size
                size--;
                // return the removed node
                return (Entry) node.item();
            }
        }
    }
    
    // key not found in the list
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    size = 0;
    for (int i = 0; i < bucketNum; i++) {
        hashtable[i] = new SList();
        // System.out.println(hashtable[i]);
    }
  }

  public boolean isPrime(int n) {
      if (n == 0 || n == 1) {
          return false;

      } else if (n == 2) {
          return true;

      } else {
          int factor = 2;
          int factorMax = (int) Math.sqrt(n);

          while (factor <= factorMax) {
            if (n % factor == 0) {
                return false;
            } else {
                factor++;
            }
          }
          return true;
      }
  }

  public String toString() {

    String results = "";
      
    for (int i = 0; i < bucketNum; i++) {
      results = results + "bucket " + i + ": " + hashtable[i].toString() + "\n";
    }
    
    return results;
  }

  public String toHistogram() {
    
    String results = "";

    for (int i = 0; i < bucketNum; i++) {
      results = results + i + " ";
      int listLength = hashtable[i].length();
      for (int j = 0; j < listLength; j++) {
          results = results + "*";
      }
      results += "\n";          
    }
    return results;
  }

  public String evaluateCollisions() {
    double expectedCollisions = size - bucketNum + bucketNum * Math.pow((1 - 1.0 / bucketNum), size);
    String results = "number of buckets: " + bucketNum + "\n";
    results += "number of entries: " + size + "\n";
    results += "Expected collisions: " + Math.round(expectedCollisions) + "\n";
    results += "Actual collisions:" + collisions;
    return results;
  }
}
