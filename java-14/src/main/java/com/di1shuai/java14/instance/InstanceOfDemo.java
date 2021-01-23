package com.di1shuai.java14.instance;

/**
 * @author Shea
 * @date 2021-01-22
 * @description
 */
public class InstanceOfDemo {

    public static void main(String[] args) {
        Object obj = "  hello Java 14  ";
        // < 14
        if (obj instanceof String) {
            System.out.println(((String) obj).strip());
        }

        // 14
        if (obj instanceof String str) {
            System.out.println(str.strip());
        }


    }

}
