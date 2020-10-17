package com.di1shuai.base.concurrent.producerconsumer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bruce
 * @date: 2019-10-24
 * @description:
 */
public class LockAwaitSignal {

    public static void main(String[] args) {
        LockData data = new LockData();
        data.producer(3,10);
        data.consumer(3,10);
    }

}

class LockData implements Data{

    private int data = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {

            //1 判断
            while (data != 0) {
                //等待
                condition.await();
            }
            //2 do
            System.out.println(Thread.currentThread().getName() + "\t" + (++data));
            //3 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {

            //1 判断
            while (data == 0) {
                //等待
                condition.await();
            }
            //2 do
            System.out.println(Thread.currentThread().getName() + "\t" + (--data));
            //3 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}




