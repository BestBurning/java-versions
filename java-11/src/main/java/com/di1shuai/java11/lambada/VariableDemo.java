package com.di1shuai.java11.lambada;

import java.util.HashMap;

/**
 * @author Shea
 * @date 2021-01-22
 * @description var变量可用于lambada表达式
 */
public class VariableDemo {

    public static void main(String[] args) {
        var map = new HashMap<String, Object>();
        map.put("Hello", "Java11");
        map.put("hi", 11);
        map.forEach((var k, var v) -> {
            System.out.println(k + ": " + v);
        });

    }

}
