package Lab5;

public class N extends M {
    
    public int myMethod(int i) {
        return i + 2;
    }
    public int useSuper(int i) {
        return super.myMethod(i);
    }

    public static void main(String[] args) {
        N n = new N();
        System.out.println(n.myMethod(0));
        System.out.println(((M) n).myMethod(0));
        // still calls the overridden method after the casting

        M m = new M();
        System.out.println(m.myMethod(0)); // calls the superclass method
        // System.out.println(((N) m).myMethod(0)); // error, can't cast

        N x = new N();
        System.out.println(x.useSuper(0));


    }
}
