public class HW0 {
    // find the max using a for loop
    public static int max(int[] a) {
        
        int current_max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] > current_max) {
                current_max = a[i];
            }
        }
        return current_max;
    }

    // find the max using a while loop
    public static int max_while(int[] a) {

        int current_max = a[0];
        int i = 0;
        while (i < a.length) {
            if (a[i] > current_max) {
                current_max = a[i];
            }
        }
        return current_max;
    }

    // if there are three integers (not neccesarily distinct) in a[] whose sum is zero
    public static boolean threeSum(int[] a) {
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // if there are three integers (distinct) in a[] whose sum is zero
    public static boolean threeSumDistinct(int[] a) {

        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;        
    }
}