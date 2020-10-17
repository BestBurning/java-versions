package com.di1shuai.base.concurrent.producerconsumer;

/**
 * @author: Bruce
 * @date: 2019-10-24
 * @description:
 */
public class SyncWaitNotify {

    public static void main(String[] args) {
        Data data = new SyncData();
        data.producer(3,10);
        data.consumer(3,10);
    }

}

class SyncData implements Data{

    private int data = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        while (data!=0){
            //等待
            this.wait();
        }
        //do
        System.out.println(Thread.currentThread().getName()+"\t"+(++data));
        //通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (data==0){
            //等待
            this.wait();
        }
        //do
        System.out.println(Thread.currentThread().getName()+"\t"+(--data));
        //通知
       this.notifyAll();
    }

}


