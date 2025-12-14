package com.tha2015.jdklearning;

import org.teavm.jso.JSExport;

public class Main {
    public static void main(String[] args) {
        System.out.println(toUpperCaseJava("hello"));
    }


    @JSExport
    public static String toUpperCaseJava(String input) {
        return input.toUpperCase();
    }

}
