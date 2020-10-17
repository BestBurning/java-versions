package com.di1shuai.base.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bruce
 * @date: 2019-10-21
 * @description:
 * 可重入锁，
 * 同一个线程获取锁后，内层递归函数仍然能够获取该锁的代码
 * 线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 * synchonized
 *
 *
 * ReentrantLock
 *
 *
 */
public class ReentrantLockDemo {



    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();


        System.out.println("======================");
        Runnable runnable = new Runnable() {
            ReentrantLock lock = new ReentrantLock();

            @Override
            public void run() {
                go();
            }

            public void go(){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+" : go");
                    to();
                }finally {
                    lock.unlock();
                }
            }
            public void to(){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+" : to");
                }finally {
                    lock.unlock();
                }
            }
        };

        new Thread(runnable,"t3").start();
        new Thread(runnable,"t4").start();



    }

}

class Phone{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+": send SMS");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+": send Email");
    }

}
