package Project1;

/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */


public class RunLengthEncoding implements Iterable {
    /**
     *  Define any variables associated with a RunLengthEncoding object here.
    *  These variables MUST be private.
    */   
    DList encodingList = new DList();
    int width;
    int height;

    /**
     *  The following methods are required for Part II.
     */

    /**
     *  RunLengthEncoding() (with two parameters) constructs a run-length
     *  encoding of a black PixImage of the specified width and height, in which
     *  every pixel has red, green, and blue intensities of zero.
     *
     * @param width the width of the image.
     * @param height the height of the image.
     */

    public RunLengthEncoding(int width, int height) {
        this.width = width;
        this.height = height;
        pixel blackPixel = new pixel();
        encodingList.insertFront(blackPixel, width * height);
    }

    /**
     *  RunLengthEncoding() (with six parameters) constructs a run-length
     *  encoding of a PixImage of the specified width and height.  The runs of
     *  the run-length encoding are taken from four input arrays of equal length.
     *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
     *  blue[i].
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     *  @param red is an array that specifies the red intensity of each run.
     *  @param green is an array that specifies the green intensity of each run.
     *  @param blue is an array that specifies the blue intensity of each run.
     *  @param runLengths is an array that specifies the length of each run.
     *
     *  NOTE:  All four input arrays should have the same length (not zero).
     *  All pixel intensities in the first three arrays should be in the range
     *  0...255.  The sum of all the elements of the runLengths array should be
     *  width * height.  (Feel free to quit with an error message if any of these
     *  conditions are not met--though we won't be testing that.)
     */

    public RunLengthEncoding(int width, int height, int[] red, int[] green, int[] blue, int[] runLengths) {
        if (width <= 0 || height <= 0) {
            System.out.println("ERROR: width and height must be positive integers.");
            return;
        }
        int pixelNum = width * height;
        this.width = width;
        this.height = height;

        for (int i = 0; i < runLengths.length; i++) {
            if (red[i] < 0 || red[i] > 255 || green[i] < 0 || green[i] > 255 || blue[i] < 0 || blue[i] > 255) {
                System.out.println("ERROR: RGB intensity must be between 0-255.");
                return;
            }
            pixel p = new pixel(red[i], green[i], blue[i]);
            encodingList.insertEnd(p, runLengths[i]);
            pixelNum -= runLengths[i];
        }                   
        if (pixelNum != 0) {
            System.out.println("ERROR: the sum of all elements of the runLengths array doesn't equal to width * height.");
            return;
        }
    }

    /**
     *  getWidth() returns the width of the image that this run-length encoding
     *  represents.
     *
     *  @return the width of the image that this run-length encoding represents.
     */

    public int getWidth() {
        // Replace the following line with your solution.
        return width;
    }

    /**
     *  getHeight() returns the height of the image that this run-length encoding
     *  represents.
     *
     *  @return the height of the image that this run-length encoding represents.
     */
    public int getHeight() {
        // Replace the following line with your solution.
        return height;
    }

    /**
     *  iterator() returns a newly created RunIterator that can iterate through
     *  the runs of this RunLengthEncoding.
     *
     *  @return a newly created RunIterator object set to the first run of this
     *  RunLengthEncoding.
     */
    public RunIterator iterator() {
        // Replace the following line with your solution.
        RunIterator it = new RunIterator(encodingList.head);
        return it;
        // You'll want to construct a new RunIterator, but first you'll need to
        // write a constructor in the RunIterator class.
    }

    /**
     *  toPixImage() converts a run-length encoding of an image into a PixImage
     *  object.
     *
     *  @return the PixImage that this RunLengthEncoding encodes.
     */
    public PixImage toPixImage() {
        PixImage image = new PixImage(width, height);
        RunIterator it = iterator();
        int cursor = 0;

        while (it.hasNext()) {
            int[] run = it.next();
            int runLength = run[0];
            short red = (short) run[1];
            short green = (short) run[2];
            short blue = (short) run[3];
            for (int positionInRun = 0; positionInRun < runLength; positionInRun++) {
                int index = positionInRun + cursor;
                int x = index % width;
                int y = (int) Math.floor(index / width);
                // System.out.println("index:" + index + " x:" + x + " y:" + y + " red:" + red);
                image.setPixel(x, y, red, green, blue);
            }
            cursor += runLength;
        }  
        // System.out.println("encodingList: \n" + this);
        // System.out.println("constructed image: \n" + image);
        return image;
    }

    /**
     *  toString() returns a String representation of this RunLengthEncoding.
     *
     *  This method isn't required, but it should be very useful to you when
     *  you're debugging your code.  It's up to you how you represent
     *  a RunLengthEncoding as a String.
     *
     *  @return a String representation of this RunLengthEncoding.
     */
    public String toString() {
        return encodingList.toString();
    }


    /**
     *  The following methods are required for Part III.
     */

    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of a specified PixImage.
     * 
     *  Note that you must encode the image in row-major format, i.e., the second
     *  pixel should be (1, 0) and not (0, 1).
     *
     *  @param image is the PixImage to run-length encode.
     */
    public RunLengthEncoding(PixImage image) {
        width = image.getWidth();
        height = image.getHeight();
        pixel prevPixel = null;

        // loop over each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // get the RGB intensities
                short r = image.getRed(x, y);
                short g = image.getGreen(x, y);
                short b = image.getBlue(x, y);
                // use the RGB to construct the current pixel
                pixel currentPixel = new pixel(r, g, b);
                // if the current pixel has the same RGB values as the previous one
                if (currentPixel.equals(prevPixel)) {
                    // belongs to the same run as the previous pixel
                    // add 1 to the run length
                    encodingList.head.prev.runLength++;
                } else {
                    // start of a new run
                    // System.out.println("currentPixel:" + currentPixel + " prevPixel:" + prevPixel);
                    encodingList.insertEnd(currentPixel, 1);
                    prevPixel = currentPixel;
                }
            }
        }
        // System.out.println("encodingList:" + encodingList);
        check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same RGB intensities, or if the sum of
     *  all run lengths does not equal the number of pixels in the image.
     */
    public void check() {
        RunIterator it = new RunIterator(encodingList.head);
        pixel prevPixel = new pixel(-1, -1, -1);
        int counter = 0;
        while (it.hasNext()) {
            int[] node = it.next();
            pixel currentPixel = new pixel(node[1], node[2], node[3]);
            if (currentPixel.equals(prevPixel)) {
                System.out.println("ERROR: two consecutive runs have the same pixel intensities.");
                return;
            }
            int runLength = node[0];
            if (runLength < 1) {
                System.out.println("ERROR: run length < 1. runLength:" + runLength);
                return;
            }
            counter += runLength;
            prevPixel = currentPixel;
        }
        if (counter != width * height) {
            System.out.println("ERROR: sum of run lengths doesn't equal to width * height of the image.");
            return;
        }
    }

    /**
     *  The following method is required for Part IV.
     */

    /**
     *  setPixel() modifies this run-length encoding so that the specified color
     *  is stored at the given (x, y) coordinates.  The old pixel value at that
     *  coordinate should be overwritten and all others should remain the same.
     *  The updated run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs with exactly the same RGB color.
     *
     *  @param x the x-coordinate of the pixel to modify.
     *  @param y the y-coordinate of the pixel to modify.
     *  @param red the new red intensity to store at coordinate (x, y).
     *  @param green the new green intensity to store at coordinate (x, y).
     *  @param blue the new blue intensity to store at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        int index = y * width + x + 1;
        pixel prevPixel = new pixel(-1, -1, -1);
        RunIterator it = new RunIterator(encodingList.head);
        while (it.hasNext()) {
            int[] run = it.next();
            int runLength = run[0];
            // System.out.println("index:" + index + " runLength:" + runLength);
            if (index > runLength) {
                // the pixel is in a later run
                index = index - runLength;
                prevPixel = new pixel(run[1], run[2], run[3]);
                continue;
            } else {
                // the new pixel is in the current run
                pixel newPixel = new pixel(red, green, blue);
                pixel currentPixel = it.node.p;
                if (newPixel.equals(currentPixel)) {
                    // the intensities set for this pixel is the same as the original intensities
                    return;
                }
                
                if (runLength == 1) {
                    // pixel is in an individual run

                    if (newPixel.equals(it.node.prev.p) && newPixel.equals(it.node.next.p)) {
                        // pixel will connect the prev and next run

                        // add the length of the current pixel
                        it.node.prev.runLength += 1;
                        // add the length of the next run
                        int runLengthNext = it.node.next.runLength;
                        it.node.prev.runLength += runLengthNext;
                        // exclude the current node and the next node, as they have been counted in the prev run
                        it.node.next.next.prev = it.node.prev;
                        it.node.prev.next = it.node.next.next;
                        return;

                    } else if (it.node.prev.p.equals(newPixel)) {
                        // pixel will merge into the prev run

                        // add length of the current pixel
                        it.node.prev.runLength += 1;
                        // exclude the current node
                        it.node.next.prev = it.node.prev;
                        it.node.prev.next = it.node.next;
                        return;

                    } else if (it.node.next.p.equals(newPixel)) {
                        // pixel will merge into the next run

                        // add length of the current pixel
                        it.node.next.runLength += 1;
                        // exclude the current node
                        it.node.next.prev = it.node.prev;
                        it.node.prev.next = it.node.next;
                        return;

                    } else {
                        // pixel will have an individual run

                        it.node.p = newPixel;
                        return;
                    }

                } else if (index == 1) {
                    // pixel is at the beginning of a longer run

                    if (it.node.prev.p.equals(newPixel)) {
                        // pixel will merge into the prev run

                        // add length of the current pixel to the prev run
                        it.node.prev.runLength++;
                        // subtract length of the current pixel from the current run
                        it.node.runLength--;
                        return;

                    } else {
                        // pixel will have an individual run

                        // subtract length of the current pixel from the current run
                        it.node.runLength--;
                        // create a new node
                        DListNode newRun = new DListNode(newPixel, 1);
                        // insert the new node between the current run and the previous run
                        newRun.next = it.node;
                        newRun.prev = it.node.prev;
                        it.node.prev.next = newRun;
                        it.node.prev = newRun;
                        return;
                    }

                } else if (index == runLength) {
                    // pixel is at the end of a longer run

                    if (it.node.next.p.equals(newPixel)) {
                        // pixel will merge into the next run

                        // add length of the current pixel into the next run
                        it.node.next.runLength++;
                        // subtract length of the current pixel from the current run
                        it.node.runLength--;
                        return;

                    } else {
                        // pixel will have an individual run

                        // subtract length of the current pixel from the current run
                        it.node.runLength--;
                        // create a new node
                        DListNode newRun = new DListNode(newPixel, 1);
                        // insert the new node between the current run and the next run
                        newRun.next = it.node.next;
                        newRun.prev = it.node;
                        it.node.next.prev = newRun;
                        it.node.next = newRun;
                        return;
                    }
                
                } else {
                    // pixel is in the middle of a longer run, and will break the run 
                    // to a prev run, a run with one pixel (this pixel), and a later run
                    
                    // create a new node for this pixel
                    DListNode pixelRun = new DListNode(newPixel, 1);

                    // calculate the runLength of the current run (which will be the prev run of the pixel run)
                    int prevRunLength = index - 1;
                    // prev run: it.node
                    it.node.runLength = prevRunLength;

                    // calculate the runLength of the later run
                    int laterRunLength = runLength - prevRunLength - 1;

                    // create a new node for the later run
                    DListNode laterRun = new DListNode(new pixel(run[1], run[2], run[3]), laterRunLength); // pixel intensity is the same for prev/later run

                    // connect the nodes
                    pixelRun.next = laterRun;
                    pixelRun.prev = it.node;
                    laterRun.next = it.node.next;
                    laterRun.prev = pixelRun;
                    it.node.next.prev = laterRun;
                    it.node.next = pixelRun;
                    return;
                }
            }

        }
        check();
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
                // System.out.println("pixels[x][y]:" + (short) pixels[x][y]);
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                            (short) pixels[x][y]);
            }
        }
        return image;
    }

    /**
     * setAndCheckRLE() sets the given coordinate in the given run-length
     * encoding to the given value and then checks whether the resulting
     * run-length encoding is correct.
     *
     * @param rle the run-length encoding to modify.
     * @param x the x-coordinate to set.
     * @param y the y-coordinate to set.
     * @param intensity the grayscale intensity to assign to pixel (x, y).
     */
    private static void setAndCheckRLE(RunLengthEncoding rle, int x, int y, int intensity) {
        rle.setPixel(x, y, (short) intensity, (short) intensity, (short) intensity);
        rle.check();
    }

    /**
     * main() runs a series of tests of the run-length encoding code.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(
            new int[][] { 
                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                        "on a 3x3 image.  Input image:");
        System.out.print(image1);
        RunLengthEncoding rle1 = new RunLengthEncoding(image1);
        rle1.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
            "RLE1 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(image1.equals(rle1.toPixImage()),
            "image1 -> RLE1 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 42);
        image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            /*
                        array2PixImage(new int[][] { 
                            { 42, 3, 6 },
                            { 1, 4, 7 },
                            { 2, 5, 8 } })),
            */
            "Setting RLE1[0][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 0, 42);
        image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[1][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 1, 2);
        image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 0);
        image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[0][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 7);
        image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[2][2] = 7 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 42);
        image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[2][2] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 2, 42);
        image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[1][2] = 42 fails.");


        PixImage image2 = array2PixImage(
            new int[][] { 
                { 2, 3, 5 },
                { 2, 4, 5 },
                { 3, 4, 6 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                        "on another 3x3 image.  Input image:");
        System.out.print(image2);
        RunLengthEncoding rle2 = new RunLengthEncoding(image2);
        rle2.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
            "RLE2 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(rle2.toPixImage().equals(image2),
            "image2 -> RLE2 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 0, 1, 2);
        image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
            "Setting RLE2[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 2, 0, 2);
        image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
            "Setting RLE2[2][0] = 2 fails.");


        PixImage image3 = array2PixImage(
            new int[][] { 
                { 0, 5 },
                { 1, 6 },
                { 2, 7 },
                { 3, 8 },
                { 4, 9 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                        "on a 5x2 image.  Input image:");
        System.out.print(image3);
        RunLengthEncoding rle3 = new RunLengthEncoding(image3);
        rle3.check();
        System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
        doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
            "RLE3 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 5x2 encoding.");
        doTest(rle3.toPixImage().equals(image3),
            "image3 -> RLE3 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 4, 0, 6);
        image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[4][0] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 1, 6);
        image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[0][1] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 0, 1);
        image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[0][0] = 1 fails.");


        PixImage image4 = array2PixImage(
            new int[][] { 
                { 0, 3 },
                { 1, 4 },
                { 2, 5 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                        "on a 3x2 image.  Input image:");
        System.out.print(image4);
        RunLengthEncoding rle4 = new RunLengthEncoding(image4);
        rle4.check();
        System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
        doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
            "RLE4 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x2 encoding.");
        doTest(rle4.toPixImage().equals(image4),
            "image4 -> RLE4 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 2, 0, 0);
        image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[2][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 0);
        image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[1][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 1);
        image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[1][0] = 1 fails.");
    }
}
