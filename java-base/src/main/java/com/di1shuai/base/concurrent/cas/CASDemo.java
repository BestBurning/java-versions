package com.di1shuai.base.concurrent.cas;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bruce
 * @date: 2019-10-20
 * @description:
 */
public class CASDemo {

    public static int initNumber = 10;

    public static AtomicInteger atomicInteger = new AtomicInteger(initNumber);

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<Integer, String>(){
            {
                put(1,"A");
                put(2,"B");
                put(3,"C");
                put(4,"D");
            }
        };
        map.forEach((k,v)->{
            threadCAS(initNumber,k,v);
        });

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("now -> "+map.get(atomicInteger.get()) + " : " + atomicInteger.get());
    }

    public static void threadCAS(int expect,int update,String threadName){
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" : "+
                    atomicInteger.compareAndSet(expect, update));
        },threadName).start();
    }

}
