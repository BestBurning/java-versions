package com.di1shuai.base.concurrent.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bruce
 * @date: 2019-10-18
 * @description:
 */
public class VolatileDemo {


    public static void main(String[] args) {

        MyData myData = new MyData();
        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("atomic:"+ myData.atomicInteger);
        System.out.println("volatile:"+ myData.number);

    }




    /**
     * volatile关键字可以保证可见性，但是也存在一个问题，就是没办法保证原子性
     */
    private static void demo2() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(myData.getNumber());
    }

    /**
     * volatile可以保证线程之间的变量可见性，变量被修改后，及时通知其他线程
     */
    private static void demo1() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" : " + myData.getNumber());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo();
            System.out.println(Thread.currentThread().getName()+" : " + myData.getNumber());

        }, "aaa").start();
        while (myData.getNumber()==0){

        }
        System.out.println(Thread.currentThread().getName()+" : " + myData.getNumber());
    }

}


class MyData {

    volatile Integer number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public Integer getNumber() {
        return number;
    }

    public void addPlusPlus(){
        this.number++;
    }

    public void addTo() {
        this.number = 60;
    }

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }

}