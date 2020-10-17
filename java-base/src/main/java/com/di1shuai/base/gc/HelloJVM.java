package com.di1shuai.base.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019/10/29
 * @description:
 */
public class HelloJVM {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("RUN HelloJVM");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
