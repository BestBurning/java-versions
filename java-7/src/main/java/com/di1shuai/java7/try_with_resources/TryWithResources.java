package com.di1shuai.java7.try_with_resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author Shea
 * @date 2021-01-21
 * @description
 */
public class TryWithResources {


    public static void main(String[] args) throws IOException {
        System.out.println(readDataBefore7("TryWithResources"));
        System.out.println(readDataAfter7("TryWithResources"));
    }

    static String readDataBefore7(String message) throws IOException {
        Reader inputString = new StringReader(message);
        BufferedReader br = new BufferedReader(inputString);
        try {
            return br.readLine();
        }finally {
            br.close();
        }

    }

    static String readDataAfter7(String message) throws IOException {
        Reader inputString = new StringReader(message);
        try (BufferedReader br = new BufferedReader(inputString)) {
            return br.readLine();
        }
    }


}
