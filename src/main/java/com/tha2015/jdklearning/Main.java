package com.tha2015.jdklearning;

import org.teavm.jso.JSExport;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, JDK Learning!");
        System.out.println("Hi");
    }

    /**
     * A method that accepts a string input and returns a string output.
     * This method is exported to JavaScript via @JSExport annotation.
     *
     * @param input The input string
     * @return A greeting message with the input
     */
    @JSExport
    public static String processString(String input) {
        if (input == null || input.isEmpty()) {
            return "Error: Input is empty!";
        }

        return "Hello, " + input + "! You sent: " + input.length() + " characters.";
    }

    /**
     * Another example: reverse a string
     */
    @JSExport
    public static String reverseString(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Example: uppercase conversion
     */
    @JSExport
    public static String toUpperCase(String input) {
        if (input == null) {
            return null;
        }
        return input.toUpperCase();
    }
}
