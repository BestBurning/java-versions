package com.di1shuai.base.concurrent.blockingqueue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-23
 * @description:
 *
 * 生产一个消费一个
 *
 *
 */
public class SychronousQueueDemo {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "c", "d");

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            list.forEach((s)->{
                System.out.println(Thread.currentThread().getName()+">put -> "+s+" 进行中");
                try {
                    blockingQueue.put(s);
                    System.out.println(Thread.currentThread().getName()+">put -> "+s+" 完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        },"T1").start();
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+">take -> "+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();

    }



}
