package Homework1;

import java.io.*;

class Nuke2 {

    public static void main(String[] arg) throws IOException {
        
        // read a string from the keyboard
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder(keyboard.readLine());

        // print the same string, with the second character removed
        // test cases will only be strings with 2 or more characters
        input.delete(1, 2);
        System.out.println(input);
    }
}
