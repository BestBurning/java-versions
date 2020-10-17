package com.di1shuai.base.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Bruce
 * @date: 2019-10-21
 * @description: 自旋锁
 * <p>
 * 自旋锁主要是采用循环方式不断去获取锁
 */
public class SpinLockDemo {

    AtomicReference<Thread> cas = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" come in");
        while (!cas.compareAndSet(null, thread)) {
            //do
        }
        System.out.println(Thread.currentThread().getName()+" lock");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        cas.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+" unlock");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "t2").start();


    }

}
