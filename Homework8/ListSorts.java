package Homework8;

/* ListSorts.java */

import Homework8.list.*;

public class ListSorts {

  private final static int SORTSIZE = 10000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    // create the new queue of queues to be returned
    LinkedQueue newQueue = new LinkedQueue();
    // while q is not empty
    while (!q.isEmpty()) {
      try {
        // take the first item in the queue
        Object o = q.dequeue();
        // make a child queue with only this item
        LinkedQueue childQueue = new LinkedQueue();
        childQueue.enqueue(o);
        // put the child queue into the queue of queues
        newQueue.enqueue(childQueue);
      } catch (QueueEmptyException e) {
        // this error should not appear
        System.out.println("ERROR: Queue is empty.");
      }
    }
    return newQueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    // create a sorted queue to be returned
    LinkedQueue sortedQueue = new LinkedQueue();
    // while neither q1 nor q2 is empty
    while (!q1.isEmpty() && !q2.isEmpty()) {
        try {
            // get the first item in each queue
            Object item1 = q1.front();
            Object item2 = q2.front();
            // put the smaller one to the end of sorted queue
            if (((Comparable) item1).compareTo(item2) < 0) {
                q1.dequeue();
                sortedQueue.enqueue(item1);
            } else {
                q2.dequeue();
                sortedQueue.enqueue(item2);
            }

        } catch (QueueEmptyException e) {
            System.out.println("ERROR: Queue is empty.");
        }
    }
    // at least one queue is empty
    if (!q1.isEmpty()) {
        // attach the remaining of q1 to the end of sorted queue
        sortedQueue.append(q1);
    } else if (!q2.isEmpty()) {
        // attach the remaining of q2 to the end of sorted queue
        sortedQueue.append(q2);
    }
    return sortedQueue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    // System.out.println("qIn:" + qIn);
    try {
        // while qIn is not empty
        while (!qIn.isEmpty()) {
            // put the first item of qIn to qSmall, qEquals, and qLarge
            // according to their relationship to the pivot.
            Comparable o = (Comparable) qIn.dequeue();
            if (o.compareTo(pivot) < 0) {
                qSmall.enqueue(o);
            } else if (o.compareTo(pivot) > 0) {
                qLarge.enqueue(o);
            } else {
                qEquals.enqueue(o);
            }
        }
    } catch (QueueEmptyException e) {
        System.out.println("ERROR: Queue is empty.");
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    try {
        LinkedQueue qoq = makeQueueOfQueues(q);
        while (qoq.size() > 1) {
            LinkedQueue q1 = (LinkedQueue) qoq.dequeue();
            LinkedQueue q2 = (LinkedQueue) qoq.dequeue();
            LinkedQueue sortedQueue = mergeSortedQueues(q1, q2);
            qoq.enqueue(sortedQueue);
        }
        q.append((LinkedQueue) qoq.dequeue());
    } catch (QueueEmptyException e) {
        System.out.println("ERROR: Queue is empty.");
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    // if q is empty or only contains 1 item, no sorting is needed
    if (q.size() <= 1) {
        return;
    }
    // find a random pivot
    int randomIndex = (Integer.valueOf((int) (q.size() * Math.random())));
    // System.out.println("randomIndex:" + randomIndex);
    Comparable pivot = (Comparable) q.nth(randomIndex);
    // System.out.println("pivot:" + pivot);
    // initialize 3 queues for partition
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEqual = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    // partition q into 3 queues
    partition(q, pivot, qSmall, qEqual, qLarge);
    // System.out.println("qSmall:" + qSmall);
    // System.out.println("qEqual:" + qEqual);
    // System.out.println("qLarge:" + qLarge);
    // recursively sort the smaller and larger partitions
    quickSort(qSmall);
    quickSort(qLarge);
    // append sorted items back to q
    q.append(qSmall);
    q.append(qEqual);
    q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(Integer.valueOf((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    System.out.println("Testing merge sort...");
    LinkedQueue q = makeRandom(10);
    System.out.println("Unsorted queue:");
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println("Sorted queue:");
    System.out.println(q.toString());
    System.out.println("");

    System.out.println("Testing quick sort...");
    q = makeRandom(10);
    System.out.println("Unsorted queue:");
    System.out.println(q.toString());
    quickSort(q);
    System.out.println("Sorted queue:");
    System.out.println(q.toString());
    System.out.println("");

    // PART III
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
