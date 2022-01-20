package Lab5;
// sub class
// this subclass inherits superclass A and implements interface B
// A and B contain methods with the same name and prototype
public class C extends A implements B {
    public static void main(String[] args) {
        
        System.out.println("(a)");
        System.out.println("Java can compile class C.");
        // C c = new C();
        //c.myMethod(3);
        System.out.println("Even if this subclass extends a superclass that contains a method with the same prototype and signiture as the implemented interface");
        
        System.out.println("(b)");
        System.out.println("When the method in the interface has a different return type, the program can't compile.");
        System.out.println("The return types are incompatible for the inherited methods B.myMethod(int), A.myMethod(int)");

        System.out.println("(c)");
        System.out.println("When the method declaration in the interface has the same return type, but a signature with a different parameter type, the program can't compile.");
        System.out.println("The type C must implement the inherited abstract method B.myMethod");

        System.out.println("(d)");
        System.out.println("What if the method declaration in the interface has the same return type, and the same number of parameters and parameter types, but those parameters have different names?");
        System.out.println("It doesn't make a difference. The program can compile.");
        
    }


}
