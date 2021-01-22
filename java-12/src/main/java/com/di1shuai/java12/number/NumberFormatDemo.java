package com.di1shuai.java12.number;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Shea
 * @date 2021-01-22
 * @description
 */
public class NumberFormatDemo {

    public static void main(String[] args) {
        long number = 100000;
        NumberFormat nfChina = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        System.out.println(nfChina.format(number));
        //10万
        NumberFormat nfUK = NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.SHORT);
        System.out.println(nfUK.format(number));
        //100K

    }

}
