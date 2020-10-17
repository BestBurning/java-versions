package com.di1shuai.base.run;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author: Bruce
 * @date: 2020-03-03
 * @description:
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        processList();

//        latch();
    }

    private static void latch() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("cmd /c D:\\mhxy\\my.exe");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processList() throws IOException {
        // 创建系统进程
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        Process p = pb.start();
        BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
        BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));
        System.out.println("Window 系统进程列表");
        String ostr;

        while ((ostr = out.readLine()) != null)
            System.out.println(ostr);
        String estr = err.readLine();
        if (estr != null) {
            System.out.println("\nError Info");
            System.out.println(estr);
        }
    }


}
