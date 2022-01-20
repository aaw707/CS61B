package Lab5;

public class K extends I implements J {
    public static void main(String[] args) {
        // (a)
        // can compile when a is not used, even if a has different value in superclass/interface

        // (b)
        // System.out.println(a);
        // can't compile. The field a is ambiguous
        // can't compile even if a in superclass/interface have the same value

        // (c)
        // access a in superclass/interface
        System.out.println(I.a);
        System.out.println(J.a);
    }
}
