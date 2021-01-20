package com.di1shuai.base.errexce;

/**
 * @author: Shea
 * @date: 2020/12/1
 * @description:
 */
public class ClassNotFoundException {

    public static void main(String[] args) throws Throwable {
        String className = "oracle.jdbc.driver.OracleDriver";
        Class.forName(className);
//        ClassLoader.loadClass(className);
//        ClassLoader.findSystemClass(className);

    }

}
