package com.di1shuai.java17.instanceofcase;

/**
 * @author shea
 * @since 2022/11/21
 */
public class InstanceOfCase {

    public static void main(String[] args) {
        JDK17_instanceof_switch(1);
    }

    public static void JDK17_instanceof_switch(Object o) {
        switch (o) {
            case Integer i -> System.out.println(i);
            case Long l -> System.out.println(l);
            case Double d -> System.out.println(d);
            case String s -> System.out.println(s);
            default -> System.out.println("UNKNOWN");
        }
    }
    public static void JDK17_before_instanceof_switch(Object o) {
        //o instanceof Integer i 为JDK16新特性
        if (o instanceof Integer i) {
            System.out.println(i);
        } else if (o instanceof Long l) {
            System.out.println(l);
        } else if (o instanceof Double d) {
            System.out.println(d);
        } else if (o instanceof String s) {
            System.out.println(s);
        } else {
            System.out.println("UNKNOWN");
        }
    }

    public static void JDK13_switch() {
        String day = "MONDAY";
        int i = switch (day) {
            case "MONDAY" -> 1;
            case "TUESDAY" -> 2;
            case "WEDNESDAY" -> 3;
            case "THURSDAY" -> 4;
            case "FRIDAY" -> 5;
            case "SATURDAY" -> 6;
            case "SUNDAY" -> 7;
            default -> 0;
        };
        System.out.println(i);
    }
    public static void JDK12_switch() {
        String day = "MONDAY";
        switch (day) {
            case "MONDAY" -> System.out.println(1);
            case "TUESDAY" -> System.out.println(2);
            case "WEDNESDAY" -> System.out.println(3);
            case "THURSDAY" -> System.out.println(4);
            case "FRIDAY" -> System.out.println(5);
            case "SATURDAY" -> System.out.println(6);
            case "SUNDAY" -> System.out.println(7);
            default -> System.out.println(0);
        }
    }
    public static void JDK11_switch() {
        String day = "MONDAY";
        switch (day) {
            case "MONDAY":
                System.out.println(1);
                break;
            case "TUESDAY":
                System.out.println(2);
                break;
            case "WEDNESDAY":
                System.out.println(3);
                break;
            case "THURSDAY":
                System.out.println(4);
                break;
            case "FRIDAY":
                System.out.println(5);
                break;
            case "SATURDAY":
                System.out.println(6);
                break;
            case "SUNDAY":
                System.out.println(7);
                break;
            default:
                System.out.println(0);
                break;
        }
    }
}
