package com.di1shuai.java19.switchcase;

import com.di1shuai.java19.recordcase.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class SwitchCase {

    public static void main(String[] args) {
        typeTester(null);
        typeTester("s");
        typeTester(Color.RED);
        typeTester(new Point(1, 2));
        typeTester(Arrays.asList("1", 2, 0.1));

    }

    static void typeTester(Object o) {
        switch (o) {
            case null -> System.out.println("null");
            case String s -> System.out.println("String");
            case Color c -> System.out.println("Color: " + c.toString());
            case Point p -> System.out.println("Record class: " + p.toString());
            case int[] ia -> System.out.println("Array of ints of length" + ia.length);
            default -> System.out.println("Something else");
        }

    }
}
