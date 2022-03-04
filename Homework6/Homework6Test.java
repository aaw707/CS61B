package Homework6;

/* Homework6Test.java */

import Homework6.dict.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
	double fval = Math.random() * 12;
	int value = (int) fval;
	board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), Integer.valueOf(i));
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 100;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    HashTableChained table = new HashTableChained(numBoards);
    initTable(table, numBoards);

    System.out.println(table.toHistogram());
    System.out.println(table.evaluateCollisions());


    /*
    Tests for HashTableChained

    // create and print the hash table
    HashTableChained t = new HashTableChained(3);
    System.out.println("creating a table with 3 entries, estimated...");
    System.out.println(t.toString());
    System.out.println("");
    // test size()
    System.out.println("size of table should be 0: " + t.size());
    // test isEmpty()
    System.out.println("table should be empty: " + t.isEmpty());
    System.out.println("");
    // test insert()
    System.out.println("inserting { key: 12, value: 0 }...");
    t.insert(12, 0);
    System.out.println("inserting { key: 11, value: 2 }...");
    t.insert(11, 2);
    System.out.println("inserting { key: 10, value: 1 }...");
    t.insert(10, 1);
    System.out.println(t.toString());
    System.out.println(t.toHistogram());
    System.out.println("");
    // test find()
    System.out.println("looking for key = 12");    
    System.out.println(t.find(12));
    System.out.println("");
    // test remove()
    System.out.println("removing key = 12");    
    System.out.println(t.toString());
    System.out.println("");
    // test makeEmpty()
    System.out.println("emptying table");   
    t.makeEmpty(); 
    System.out.println(t.toString());
    System.out.println("");
    
    System.out.println("test ends");
  */
  }
}
