// there might be some problems in this part.. the casting should work for ya = (Y[])xa but it didn't!
package Lab5;

import java.util.Arrays;

public class X {
    public int x;  

    public X() {
        x = 0;
    }

    public X(int i) {
        x = i;
    }

    public String toString() {
        return Integer.toString(x);
    }

    public static void main(String[] args) {

        // (a)
        System.out.println("(a)");
        // xa is an array of X's
        X[] xa = {new X(1), new X(2), new X(3)};
        System.out.println("Initial value of xa:" + Arrays.toString(xa));
        // ya is an array of Y's
        Y[] ya = {new Y(4), new Y(5), new Y(6)};
        System.out.println("Initial value of ya:" + Arrays.toString(ya));

        System.out.println("");
        // can we assign xa to ya?
        System.out.println("Assigning the value of xa to ya...");
        //ya = xa; // Type mismatch: cannot convert from X[] to Y[]
        System.out.println("Can't assign the value of xa to ya");
        System.out.println("");

        // can we assign xa to ya by casting the array?
        System.out.println("Assigning the value of xa to ya by casting the array...");
        // ya = (Y[])xa; // error
        System.out.println("Can't assign the value of xa to ya by casting the array");    
        System.out.println("");    

        // can we assign xa to ya by casting each item in the array?
        System.out.println("Assigning the value of xa to ya by casting each item in the array");
        /*
        for (X x : xa) {
            x = (Y) x;
        }        
        ya = xa;
        */ // error
        System.out.println("Can't assign the value of xa to ya by casting each item in the array");    
        System.out.println("");    

        // can we assign ya to xa?
        System.out.println("Assigning the value of ya to xa...");
        xa = ya;
        System.out.println("Value of xa:" + Arrays.toString(xa));
        System.out.println("Value of ya:" + Arrays.toString(ya));
        System.out.println("No casting is needed.");

        // (b)
        System.out.println("(b)");
        System.out.println("After assigning ya to xa, re-assigning it back from xa to ya");
        // ya = xa;// error
        System.out.println("Can't re-assign ya back from xa to ya");

        // (c)
        System.out.println("When xa references an array of X's, it can't be assigned to ya");
        System.out.println("When xb references an array of Y's...");
        X[] xb = {new Y(7), new Y(8), new Y(9)};
        System.out.println("Initial value of xb:" + Arrays.toString(xb));
        Y[] yb = {new Y(10), new Y(11), new Y(12)};
        System.out.println("Initial value of yb:" + Arrays.toString(yb));
        // yb = xb; // error
        // yb = (Y[]) xb; // error
        for (X x : xb) {
            x = (Y) x;
            System.out.println(x.getClass());
        }    
        System.out.println(xb.getClass());
        //yb = xb; // error
        System.out.println("the casting only changed the class of individual items, but not the array");
        
    }
}
