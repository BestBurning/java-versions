package com.di1shuai.base.errexce;

/**
 * @author: Bruce
 * @date: 2019-11-12
 * @description:
 * Exception in thread "main" java.lang.StackOverflowError
 */
public class StackOverFlowErrorDemo {

    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }

}
