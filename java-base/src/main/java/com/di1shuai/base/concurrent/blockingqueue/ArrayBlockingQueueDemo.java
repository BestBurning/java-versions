package com.di1shuai.base.concurrent.blockingqueue;

import com.di1shuai.base.concurrent.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-23
 * @description:
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);

        throwException(list, blockingQueue);
        returnObj(list, blockingQueue);
        timeout(list,blockingQueue);

        blocking(list, blockingQueue);

    }

    private static void timeout(List<String> list, BlockingQueue<String> blockingQueue) {
        StringUtil.title("超时组", 80);

        list.forEach((s) -> {
            try {
                System.out.println("offer元素->" + s + ":" + (blockingQueue.offer(s,5l, TimeUnit.SECONDS)?true:false+" 队列满了,等待了5s添加失败"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        String poll = null;
        do {
            try {
                poll = blockingQueue.poll(5l,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("poll元素->" + poll);
        } while (poll!=null);
        System.out.println("等待了5s,没有元素");
    }

    /**
     * 阻塞
     *
     * put
     *
     * take
     *
     * @param list
     * @param blockingQueue
     */
    private static void blocking(List<String> list, BlockingQueue<String> blockingQueue) {
        StringUtil.title("阻塞组", 80);

        new Thread(()->{
            int timeout = 20;
            System.out.println("线程 >"+Thread.currentThread().getName()+"< 会在20s后关闭程序");
            try {
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        },"exit").start();

        new Thread(()->{
            try {
                int timeout = 10;
                System.out.println("线程 >"+Thread.currentThread().getName()+"< 会在10s后take元素");
                TimeUnit.SECONDS.sleep(timeout);
                while (true){
                    System.out.println("线程>"+Thread.currentThread().getName()+"<take元素->"+ blockingQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"other").start();

        list.forEach((s) -> {
            try {
                System.out.println("put元素->" + s+" 进行中");
                blockingQueue.put(s);
                System.out.println("put元素->" + s+" 完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



    }

    /**
     * offer    满时返回false
     *
     * poll     空时返回null
     *
     * peek
     *
     * @param list
     * @param blockingQueue
     */
    private static void returnObj(List<String> list, BlockingQueue<String> blockingQueue) {
        StringUtil.title("返回值组", 80);

        list.forEach((s) -> {
            System.out.println("offer元素->" + s + ":" + (blockingQueue.offer(s)?true:false+" 队列满了,添加失败"));
        });
        //队首元素
        System.out.println("队首为->" + blockingQueue.peek());
        String poll = null;
        do {
            poll = blockingQueue.poll();
            System.out.println("poll元素->" + poll);
        } while (poll!=null);
        System.out.println("没有元素了");
    }



    /**
     * add        满时抛出      java.lang.IllegalStateException: Queue full
     * remove     空时抛出      java.util.NoSuchElementException
     * element
     *
     * @param list
     * @param blockingQueue
     */
    private static void throwException(List<String> list, BlockingQueue<String> blockingQueue) {
        StringUtil.title("抛出异常组",80);
        try {
            //java.lang.IllegalStateException: Queue full
            list.forEach((s) -> {
                System.out.println("add元素->" + s + ":" + blockingQueue.add(s));
            });
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("队列满了");
        }
        //队首元素
        System.out.println("队首为->" + blockingQueue.element());
        try {
            while (true) {
                //java.util.NoSuchElementException
                System.out.println("remove元素->" + blockingQueue.remove());
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("没有元素了");
        }
    }
}
