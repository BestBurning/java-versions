package com.di1shuai.java14.nullexception;

/**
 * @author Shea
 * @date 2021-01-23
 * @description
 *
 *  before 14
 *
 * java.lang.NullPointerException
 * 	at com.di1shuai.java14.nullexception.NullPointerExceptionDemo.main(NullPointerExceptionDemo.java:21)
 *
 *
 *  14 默认开启 -XX:+ShowCodeDetailsInExceptionMessages
 *
 * Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toString()" because "str" is null
 * 	at com.di1shuai.java14.nullexception.NullPointerExceptionDemo.main(NullPointerExceptionDemo.java:12)
 *
 */
public class NullPointerExceptionDemo {

    public static void main(String[] args) {
        String str = null;
        try {
            str.toString();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

}
