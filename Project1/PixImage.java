package Project1;
import java.lang.Math.*;
/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private int width;
  private int height;
  pixel[][] matrix;

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    if (width < 0 || height < 0) {
      System.out.println("The width and height of the image has to be positive integers.");
      return;
    }
    // Your solution here.
    this.width = width;
    this.height = height;
    this.matrix = new pixel[height][width];
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return matrix[x][y].r;
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return matrix[x][y].g;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return matrix[x][y].b;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
    if (0 <= red && 0 <= green && 0 <= blue && red <= 255 && green <= 255 && blue <= 255) {
      matrix[x][y] = new pixel(red, green, blue);
    }
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
    String dimensions = "Height: " + height + " Width: " + width;
    String matrixResults = "";
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        matrixResults = matrixResults + "[" + matrix[i][j].r + " " + matrix[i][j].g + " " + matrix[i][j].b + "] ";
      }
      matrixResults = matrixResults + "\n";
    }
    return dimensions + "\n" + matrixResults;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
    // if numIteration is invalid, return the original image
    if (numIterations <= 0) {
      return this;
    }
    PixImage currentImage = this;
    PixImage newImage = this;
    int boxSize;
    int maxHeight = height - 1;
    int maxWidth = width - 1;
    pixel target;

    // create a new image to store the result
    for (int iteration = 0; iteration < numIterations; iteration++) {
      /*
      the project description implies the images to be processed are
      at least 3*3 for height and width. therefore, the code doesn't 
      include the case for smaller images
      */

      newImage = new PixImage(this.height, this.width);

      // blur the corner pixels
      // 2x2 box with (x, y) coordinate as each element
      boxSize = 4;
      
      // top left corner      
      int[][][] topLeft = {
        {{0, 0}, {0, 1}}, 
        {{1, 0}, {1, 1}}
      };   
      target = currentImage.blurBox(topLeft, boxSize);      
      newImage.setPixel(0, 0, target.getR(), target.getG(), target.getB());

      // top right corner
      int[][][] topRight = {
        {{0, maxWidth - 1}, {0, maxWidth}}, 
        {{1, maxWidth - 1}, {1, maxWidth}}
      };
      target = currentImage.blurBox(topRight, boxSize);      
      newImage.setPixel(0, maxWidth, target.getR(), target.getG(), target.getB());
    
      // bottom left corner
      int[][][] bottomLeft = {
        {{maxHeight - 1, 0}, {maxHeight - 1, 1}}, 
        {{maxHeight, 0}, {maxHeight, 1}}
      };
      target = currentImage.blurBox(bottomLeft, boxSize);      
      newImage.setPixel(maxHeight, 0, target.getR(), target.getG(), target.getB());

      // bottom right corner
      int[][][] bottomRight = {
        {{maxHeight - 1, maxWidth - 1}, {maxHeight - 1, maxWidth}}, 
        {{maxHeight, maxWidth - 1}, {maxHeight, maxWidth}}
      };   
      target = currentImage.blurBox(bottomRight, boxSize);      
      newImage.setPixel(maxHeight, maxWidth, target.getR(), target.getG(), target.getB());

      // blur the edges
      boxSize = 6;

      // left & right edges
      // 3x2 box with (x, y) coordinate as each element    
      for (int i = 1; i < maxHeight; i++) {

        // left edge
        int[][][] leftEdge = {
          {{i - 1, 0}, {i - 1, 1}}, 
          {{i, 0}, {i, 1}}, 
          {{i + 1, 0}, {i + 1, 1}}
        };
        target = currentImage.blurBox(leftEdge, boxSize);
        newImage.setPixel(i, 0, target.getR(), target.getG(), target.getB());

        //right edge
        int[][][] rightEdge = {
          {{i - 1, maxWidth - 1}, {i - 1, maxWidth}}, 
          {{i, maxWidth - 1}, {i, maxWidth}}, 
          {{i + 1, maxWidth - 1}, {i + 1, maxWidth}}
        };
        target = currentImage.blurBox(rightEdge, boxSize);
        newImage.setPixel(i, maxWidth, target.getR(), target.getG(), target.getB());
      }

      // top & bottom edges
      // 2x3 box with (x, y) coordinate as each element  
      for (int j = 1; j < maxWidth; j++) {
        
        // top edge
        int[][][] topEdge = {
          {{0, j - 1}, {0, j}, {0, j + 1}}, 
          {{1, j - 1}, {1, j}, {1, j + 1}}
        };
        target = currentImage.blurBox(topEdge, boxSize);
        newImage.setPixel(0, j, target.getR(), target.getG(), target.getB());

        // bottom edge
        int[][][] bottomEdge = {
          {{maxHeight - 1, j - 1}, {maxHeight - 1, j}, {maxHeight - 1, j + 1}}, 
          {{maxHeight, j - 1}, {maxHeight, j}, {maxHeight, j + 1}}
        };
        target = currentImage.blurBox(bottomEdge, boxSize);
        newImage.setPixel(maxHeight, j, target.getR(), target.getG(), target.getB());
      }

      // blur the non-corner, non-edge pixels
      boxSize = 9;
      for (int i = 1; i < maxHeight; i++) {
        for (int j = 1; j < maxWidth; j++) {
          int [][][] centerPixel = {
            {{i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1}}, 
            {{i, j - 1}, {i, j}, {i, j + 1}}, 
            {{i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1}
          }};
          target = currentImage.blurBox(centerPixel, boxSize);
          newImage.setPixel(i, j, target.getR(), target.getG(), target.getB());
        }
      }
      // set the newly blurred image as the current image, for the possible next iteration of blurring
      currentImage = newImage;
    }
    return newImage;
  }

 private pixel blurBox(int[][][] box, int boxSize) {
   // blur the pixels in the box and return the calculated pixel with averaged color   

   short blurredRed = 0;
   short blurredGreen = 0;
   short blurredBlue = 0;

   for (int[][] row : box) {
     for (int [] coordinate : row) {
       int i = coordinate[0];
       int j = coordinate[1];
       blurredRed += getRed(i, j);
       blurredGreen += getGreen(i, j);
       blurredBlue += getBlue(i, j);
     }
   }
   blurredRed = (short) (blurredRed / boxSize);
   blurredGreen = (short) (blurredGreen / boxSize);
   blurredBlue = (short) (blurredBlue / boxSize);

   pixel target = new pixel(blurredRed, blurredGreen, blurredBlue);

   return target;
 }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
    PixImage newImage = new PixImage(this.width, this.height);

    // loop over each pixel in the image
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        long energy = energy(i, j);
        short intensity = mag2gray(energy);
        newImage.setPixel(i, j, intensity, intensity, intensity);
      }
    }
    return newImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }

  public long energy(int x, int y) {

    // convolutions
    int[][] gx = {
      {1, 0, -1}, 
      {2, 0, -2},
      {1, 0, -1}
    };
    int[][] gy = {
      {1, 2, 1}, 
      {0, 0, 0},
      {-1, -2, -1}
    };

    // initialize gx and gy for each color
    int gxRed = 0;
    int gyRed = 0;

    int gxGreen = 0;
    int gyGreen = 0;

    int gxBlue = 0;
    int gyBlue = 0;  

    // get the coordinates of the 9 neighboring elements
    int[][][] box = getReflectedBox(x, y);
    
    // loop over each element in the box
    for (int m = 0; m < 3; m++) {
      for (int n = 0; n < 3; n++) {
        int xCoord = box[m][n][0];
        int yCoord = box[m][n][1];
        
        long redValue = (long) getRed(xCoord, yCoord);
        gxRed += gx[m][n] * redValue;
        gyRed += gy[m][n] * redValue;

        long greenValue = (long) getGreen(xCoord, yCoord);
        gxGreen += gx[m][n] * greenValue;
        gyGreen += gy[m][n] * greenValue;

        long blueValue = (long) getBlue(xCoord, yCoord); 
        gxBlue += gx[m][n] * blueValue;
        gyBlue += gy[m][n] * blueValue;  
      }
    }    
    long energy = gxRed*gxRed + gyRed*gyRed + gxGreen*gxGreen + gyGreen*gyGreen + gxBlue*gxBlue + gyBlue*gyBlue;
    return energy;
  }

  public int[][][] getReflectedBox(int x, int y) {
    // return the x coordinate of the 9 neighboring element in the box
    // treate pixels on the boundary by reflecting the image across each boundary
    int[][][] boxReturn;
    if (x == 0 && y == 0) {
      // top left corner
      int[][][] box = {
        {{0, 0}, {0, 0}, {0, 1}},
        {{0, 0}, {0, 0}, {0, 1}},
        {{1, 0}, {1, 0}, {1, 1}}
      };
      boxReturn = box;
    } else if (x == 0 && y == width - 1) {
      // top right corner
      int[][][] box = {
        {{0, width - 2}, {0, width - 1}, {0, width - 1}},
        {{0, width - 2}, {0, width - 1}, {0, width - 1}},
        {{1, width - 2}, {1, width - 1}, {1, width - 1}}
      };
      boxReturn = box;
    } else if (x == height - 1 && y == 0) {
      // bottom left corner
      int[][][] box = {
        {{height - 2, 0}, {height - 2, 0}, {height - 2, 1}},
        {{height - 1, 0}, {height - 1, 0}, {height - 1, 1}},
        {{height - 1, 0}, {height - 1, 0}, {height - 1, 1}}
      };
      boxReturn = box;
    } else if (x == height - 1 && y == width - 1) {
      // bottom right corner
      int[][][] box = {
        {{height - 2, width - 2}, {height - 2, width - 1}, {height - 2, width - 1}},
        {{height - 1, width - 2}, {height - 1, width - 1}, {height - 1, width - 1}},
        {{height - 1, width - 2}, {height - 1, width - 1}, {height - 1, width - 1}}
      };
      boxReturn = box;
    } else if (x == 0) {
      // top row, not corners
      int[][][] box = {
        {{0, y - 1}, {0, y}, {0, y + 1}},
        {{0, y - 1}, {0, y}, {0, y + 1}},
        {{1, y - 1}, {1, y}, {1, y + 1}}
      };
      boxReturn = box;
    } else if (x == height - 1) {
      // bottom row, not corners
      int[][][] box = {
        {{height - 2, y - 1}, {height - 2, y}, {height - 2, y + 1}},
        {{height - 1, y - 1}, {height - 1, y}, {height - 1, y + 1}},
        {{height - 1, y - 1}, {height - 1, y}, {height - 1, y + 1}}
      };
      boxReturn = box;
    } else if (y == 0) {
      // left edge, not corners
      int[][][] box = {
        {{x - 1, 0}, {x - 1, 0}, {x - 1, 1}},
        {{x, 0}, {x, 0}, {x, 1}},
        {{x + 1, 0}, {x + 1, 0}, {x + 1, 1}}
      };
      boxReturn = box;
    } else if (y == width - 1) {
      // right edge, not corners
      int[][][] box = {
        {{x - 1, width - 2}, {x - 1, width - 1}, {x - 1, width - 1}},
        {{x, width - 2}, {x, width - 1}, {x, width - 1}},
        {{x + 1, width - 2}, {x + 1, width - 1}, {x + 1, width - 1}}
      };
      boxReturn = box;
    } else {
      // center pixels
      int[][][] box = {
        {{x - 1, y - 1}, {x - 1, y}, {x - 1, y + 1}},
        {{x, y - 1}, {x, y}, {x, y + 1}},
        {{x + 1, y - 1}, {x + 1, y}, {x + 1, y + 1}}
      };
      boxReturn = box;
    }
    return boxReturn;
  }




  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
