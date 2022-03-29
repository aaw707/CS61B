package Homework9;

/* Maze.java */

import java.util.*;
import Homework9.set.*;
import Lab5.C;

/**
 *  The Maze class represents a maze in a rectangular grid.  There is exactly
 *  one path between any two points.
 **/

public class Maze {

  // Horizontal and vertical dimensions of the maze.
  protected int horiz;
  protected int vert;
  // Horizontal and vertical interior walls; each is true if the wall exists.
  protected boolean[][] hWalls;
  protected boolean[][] vWalls;

  // Object for generting random numbers.
  private static Random random;

  // Constants used in depth-first search (which checks for cycles in the
  // maze).
  private static final int STARTHERE = 0;
  private static final int FROMLEFT = 1;
  private static final int FROMRIGHT = 2;
  private static final int FROMABOVE = 3;
  private static final int FROMBELOW = 4;

  /**
   *  Maze() creates a rectangular maze having "horizontalSize" cells in the
   *  horizontal direction, and "verticalSize" cells in the vertical direction.
   *  There is a path between any two cells of the maze.  A disjoint set data
   *  structure is used to ensure that there is only one path between any two
   *  cells.
   **/
  public Maze(int horizontalSize, int verticalSize) {
    System.out.println("horizontalSize:" + horizontalSize);
    System.out.println("verticalSize:" + verticalSize);
    int i, j;

    horiz = horizontalSize;
    vert = verticalSize;
    if ((horiz < 1) || (vert < 1) || ((horiz == 1) && (vert == 1))) {
      return;                                    // There are no interior walls
    }

    // Create all of the horizontal interior walls.  Initially, every
    // horizontal wall exists; they will be removed later by the maze
    // generation algorithm.
    if (vert > 1) {
      hWalls = new boolean[horiz][vert - 1];
      for (j = 0; j < vert - 1; j++) {
        for (i = 0; i < horiz; i++) {
          hWalls[i][j] = true;
        }
      }
    }
    // Create all of the vertical interior walls.
    if (horiz > 1) {
      vWalls = new boolean[horiz - 1][vert];
      for (i = 0; i < horiz - 1; i++) {
        for (j = 0; j < vert; j++) {
          vWalls[i][j] = true;
        }
      }
    }



    /**
     * Fill in the rest of this method.  You should go through all the walls of
     * the maze in random order, and remove any wall whose removal will not
     * create a cycle.  Use the implementation of disjoint sets provided in the
     * set package to avoid creating any cycles.
     *
     * Note the method randInt() further below, which generates a random
     * integer.  randInt() generates different numbers every time the program
     * is run, so that you can make lots of different mazes.
     **/

    // create cells as disjointed sets. each item is the root of its own set
    System.out.println("Creating disjoint sets for " + horizontalSize * verticalSize + " cells");
    DisjointSets cells = new DisjointSets(horizontalSize * verticalSize);
    /* 
    say cells are ordered left to right, top to bottom
    (0, 0), (1, 0), (2, 0), (3, 0), (4, 0), (5, 0),
    (0, 1), (1, 1)...
    */

    // number of interior walls: horizontal walls + vertical walls
    int numInteriorWalls = horizontalSize * (verticalSize - 1) + (horizontalSize - 1) * verticalSize;
    // represent all interior walls as an array of ints
    System.out.println("Creating an array representing interior walls");
    int[] walls = new int[numInteriorWalls];
    for (i = 0; i < numInteriorWalls; i++) {
      walls[i] = i + 1; // nth wall
    }
    /* 
    say the walls are ordered first horizontal:
    (0, 0), (1, 0), (2, 0), (3, 0), (4, 0), (5, 0),
    (0, 1), (1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
    then vertical:
    (0, 0), (0, 1), (0, 2),
    (1, 0), (1, 1), (1, 2),
    (2, 0), (2, 1), (2, 2),
    (3, 0), (3, 1), (3, 2),
    (4, 0), (4, 1), (4, 2)
    */

    // order the walls in random order
    System.out.println("randomly assigning the order of walls");
    for (int w = numInteriorWalls; w > 1; w--) {
      // select one of the first w walls in the array at random
      int selectedIdx = randInt(w);
      int selectedWall = walls[selectedIdx];
      // swap the randomly selected wall with the wth wall
      walls[selectedIdx] = walls[w - 1];
      walls[w - 1] = selectedWall;
    }
    System.out.println("random order of the walls");
    for (i = 0; i < walls.length; i++) {
        System.out.print(walls[i] + " ");        
    }
    System.out.println("");

    // visit walls in this random order
    System.out.println("Visiting all walls");
    for (i = 0; i < numInteriorWalls; i++) {
        // get the num representing the wall
        int wall = walls[i];
        // X, Y coordinate of wall
        int wallX, wallY;
        // coordinate of adjacent cells
        int cell1X, cell1Y, cell2X, cell2Y;
        // idx of cell in disjointed sets array
        int cell1Idx, cell2Idx;
        // wall is horizontal/vertical
        boolean isHorizontal;

        // horizontal or vertical?
        if (wall <= horizontalSize * (verticalSize - 1)) {
            // horizontal wall
            isHorizontal = true;
            // wall coords
            wallY = (wall + horizontalSize - 1) / horizontalSize - 1; // ceiling int of division
            wallX = wall - wallY * horizontalSize - 1;
            // cell coords
            cell1X = wallX;
            cell1Y = wallY;
            cell2X = wallX;
            cell2Y = wallY + 1;
        } else {
            // vertial wall
            isHorizontal = false;
            int vwall = wall - horizontalSize * (verticalSize - 1);
            // wall coords
            wallX = (vwall + verticalSize - 1) / verticalSize - 1; // ceiling int of division
            wallY = vwall - wallX * verticalSize - 1;
            // cell coords
            cell1X = wallX;
            cell1Y = wallY;
            cell2X = wallX + 1;
            cell2Y = wallY;
        }
        // cell idx in disjointed sets array
        cell1Idx = cell1Y * horizontalSize + cell1X; 
        cell2Idx = cell2Y * horizontalSize + cell2X;
        System.out.println("wall index: " + i);
        System.out.println("wall num: " + wall);
        System.out.println("wallX: " + wallX);
        System.out.println("wallY: " + wallY);
        System.out.println("cell1X: " + cell1X);
        System.out.println("cell1Y: " + cell1Y);
        System.out.println("cell2X: " + cell2X);
        System.out.println("cell2Y: " + cell2Y);
        System.out.println("cell1Idx: " + cell1Idx);
        System.out.println("cell2Idx: " + cell2Idx);
        System.out.println("");
        
        // determine whether these 2 cells are in the same set i.e. already a path between
        int root1 = cells.find(cell1Idx);
        int root2 = cells.find(cell2Idx);
        System.out.println("root1: " + root1);
        System.out.println("root2: " + root2);
        if (root1 != root2) {
            // not in the same set. no path throught yet
            System.out.println("2 cells are not in the same set");
            // elimimate the wall in between
            System.out.println("elimate the wall");
            if (isHorizontal) {
                hWalls[wallX][wallY] = false;
            } else {
                vWalls[wallX][wallY] = false;
            }
            // union the sets of 2 cells 
            System.out.println("union the cells");
            cells.union(root1, root2);
        } else {
            System.out.println("2 cells are in the same set");
        }
        System.out.println("");

        

    }



  }

  /**
   *  toString() returns a string representation of the maze.
   **/
  public String toString() {
    int i, j;
    String s = "";

    // Print the top exterior wall.
    for (i = 0; i < horiz; i++) {
      s = s + "--";
    }
    s = s + "-\n|";

    // Print the maze interior.
    for (j = 0; j < vert; j++) {
      // Print a row of cells and vertical walls.
      for (i = 0; i < horiz - 1; i++) {
        if (vWalls[i][j]) {
          s = s + " |";
        } else {
          s = s + "  ";
        }
      }
      s = s + " |\n+";
      if (j < vert - 1) {
        // Print a row of horizontal walls and wall corners.
        for (i = 0; i < horiz; i++) {
          if (hWalls[i][j]) {
            s = s + "-+";
          } else {
            s = s + " +";
          }
        }
        s = s + "\n|";
      }
    }

    // Print the bottom exterior wall.  (Note that the first corner has
    // already been printed.)
    for (i = 0; i < horiz; i++) {
      s = s + "--";
    }
    return s + "\n";
  }

  /**
   * horizontalWall() determines whether the horizontal wall on the bottom
   * edge of cell (x, y) exists.  If the coordinates (x, y) do not correspond
   * to an interior wall, true is returned.
   **/
  public boolean horizontalWall(int x, int y) {
    if ((x < 0) || (y < 0) || (x > horiz - 1) || (y > vert - 2)) {
      return true;
    }
    return hWalls[x][y];
  }

  /**
   * verticalWall() determines whether the vertical wall on the right edge of
   * cell (x, y) exists. If the coordinates (x, y) do not correspond to an
   * interior wall, true is returned.
   **/
  public boolean verticalWall(int x, int y) {
    if ((x < 0) || (y < 0) || (x > horiz - 2) || (y > vert - 1)) {
      return true;
    }
    return vWalls[x][y];
  }

  /**
   * randInt() returns a random integer from 0 to choices - 1.
   **/
  private static int randInt(int choices) {
    if (random == null) {       // Only executed first time randInt() is called
      random = new Random();       // Create a "Random" object with random seed
    }
    int r = random.nextInt() % choices;      // From 1 - choices to choices - 1
    if (r < 0) {
      r = -r;                                          // From 0 to choices - 1
    }
    return r;
  }

  /**
   * diagnose() checks the maze and prints a warning if not every cell can be
   * reached from the upper left corner cell, or if there is a cycle reachable
   * from the upper left cell.
   *
   * DO NOT CHANGE THIS METHOD.  Your code is expected to work with our copy
   * of this method.
   **/
  protected void diagnose() {
    if ((horiz < 1) || (vert < 1) || ((horiz == 1) && (vert == 1))) {
      return;                                    // There are no interior walls
    }

    boolean mazeFine = true;

    // Create an array that indicates whether each cell has been visited during
    // a depth-first traversal.
    boolean[][] cellVisited = new boolean[horiz][vert];
    // Do a depth-first traversal.
    if (depthFirstSearch(0, 0, STARTHERE, cellVisited)) {
      System.out.println("Your maze has a cycle.");
      mazeFine = false;
    }

    // Check to be sure that every cell of the maze was visited.
  outerLoop:
    for (int j = 0; j < vert; j++) {
      for (int i = 0; i < horiz; i++) {
        if (!cellVisited[i][j]) {
          System.out.println("Not every cell in your maze is reachable from " +
                             "every other cell.");
          mazeFine = false;
          break outerLoop;
        }
      }
    }

    if (mazeFine) {
      System.out.println("What a fine maze you've created!");
    }
  }

  /**
   * depthFirstSearch() does a depth-first traversal of the maze, marking each
   * visited cell.  Returns true if a cycle is found.
   *
   * DO NOT CHANGE THIS METHOD.  Your code is expected to work with our copy
   * of this method.
   */
  protected boolean depthFirstSearch(int x, int y, int fromWhere,
                                     boolean[][] cellVisited) {
    boolean cycleDetected = false;
    cellVisited[x][y] = true;

    // Visit the cell to the right?
    if ((fromWhere != FROMRIGHT) && !verticalWall(x, y)) {
      if (cellVisited[x + 1][y]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x + 1, y, FROMLEFT, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell below?
    if ((fromWhere != FROMBELOW) && !horizontalWall(x, y)) {
      if (cellVisited[x][y + 1]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x, y + 1, FROMABOVE, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell to the left?
    if ((fromWhere != FROMLEFT) && !verticalWall(x - 1, y)) {
      if (cellVisited[x - 1][y]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x - 1, y, FROMRIGHT, cellVisited) ||
                        cycleDetected;
      }
    }

    // Visit the cell above?
    if ((fromWhere != FROMABOVE) && !horizontalWall(x, y - 1)) {
      if (cellVisited[x][y - 1]) {
        cycleDetected = true;
      } else {
        cycleDetected = depthFirstSearch(x, y - 1, FROMBELOW, cellVisited) ||
                        cycleDetected;
      }
    }

    return cycleDetected;
  }

  /**
   * main() creates a maze of dimensions specified on the command line, prints
   * the maze, and runs the diagnostic method to see if the maze is good.
   */
  public static void main(String[] args) {
    int x = 39;
    int y = 15;

    /**
     *  Read the input parameters.
     */

    if (args.length > 0) {
      try {
        x = Integer.parseInt(args[0]);
      }
      catch (NumberFormatException e) {
        System.out.println("First argument to Simulation is not an number.");
      }
    }

    if (args.length > 1) {
      try {
        y = Integer.parseInt(args[1]);
      }
      catch (NumberFormatException e) {
        System.out.println("Second argument to Simulation is not an number.");
      }
    }

    Maze maze = new Maze(x, y);
    System.out.print(maze);
    maze.diagnose();
  }

}
