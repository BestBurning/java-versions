package com.di1shuai.java9.try_with_resources;

import java.io.*;

/**
 * @author Shea
 * @date 2021-01-21
 * @description try-with-resources 改进
 * 在try的()内即可传入需要关闭的资源
 *
 */
public class TryWithResources {


    public static void main(String[] args) throws IOException {
        System.out.println(readDataBefore9("TryWithResources"));
        System.out.println(readDataAfter9("TryWithResources"));
    }

    static String readDataBefore9(String message) throws IOException {
        try (BufferedReader br = new BufferedReader(new StringReader(message));) {
            return br.readLine();
        }
    }

    static String readDataAfter9(String message) throws IOException {
        Reader inputString = new StringReader(message);
        BufferedReader br = new BufferedReader(inputString);
        try (br) {
            return br.readLine();
        }
    }

}
