package com.di1shuai.base.concurrent.count;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: Bruce
 * @date: 2019-10-23
 * @description:
 * 循环屏障
 *
 * 与CountDownLatch 相反，所有任务都满足后才执行相关线程的任务，即加法
 *
 */
public class CyclicBarrierDemo {



    public static void main(String[] args) {
        Set<String> countries = new HashSet<String>(){
            {
                add("中国");
                add("俄国");
                add("美国");
                add("英国");
                add("法国");
            }
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("召开五常会议");
        });

        for (String country : countries) {
            new Thread(()->{
                System.out.println(country+"到了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },country).start();
        }
    }

}
