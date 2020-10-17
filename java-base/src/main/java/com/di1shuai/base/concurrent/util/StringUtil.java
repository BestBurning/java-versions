package com.di1shuai.base.concurrent.util;

/**
 * @author: Bruce
 * @date: 2019-10-25
 * @description:
 */
public class StringUtil {

    private static final int titleNumber = 80;

    /**
     * 打印标题
     * e.g. title(标题)
     * @param title  标题
     */
    public static void title(String title) {
        title(title,titleNumber);
    }


    /**
     * 打印标题
     * e.g. title(标题,80)
     * @param title  标题
     * @param n      长度
     */
    public static void title(String title, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("=");
        }
        System.out.println();
        int length = title.length();
        if (n-length<2){
            System.out.println(title);
        }else {
            for (int i = 0; i < (n-length)/2; i++) {
                System.out.print(" ");
            }
            System.out.println(title);
        }
        for (int i = 0; i < n; i++) {
            System.out.print("=");
        }
        System.out.println();
    }


}
