package com.di1shuai.base.concurrent.count;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-23
 * @description: 信号
 * 有限的资源的使用
 * <p>
 * 可以抢占、可以释放
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws IOException {
        demo();
    }

    private static void demo() {
        Semaphore semaphore = new Semaphore(3, false);
        for (int i = 1; i <= 11; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " -> 抢到");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" " + Thread.currentThread().getName() + " -> 释放");
                    semaphore.release();

                }
            }, String.valueOf(i)).start();
        }
    }

}
