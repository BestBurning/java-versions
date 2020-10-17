package com.di1shuai.base.concurrent.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bruce
 * @date: 2019-10-24
 * @description:
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueData blockingQueueData = new BlockingQueueData(new ArrayBlockingQueue<String>(5));
        for (int i = 1; i <=3 ; i++) {
            new Thread(()->{
                try {
                    blockingQueueData.prod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"P-"+i).start();
        }
        for (int i = 1; i <=3 ; i++) {
            new Thread(()->{
                try {
                    blockingQueueData.comsume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"C-"+i).start();
        }

        TimeUnit.SECONDS.sleep(5);
        blockingQueueData.stop();
    }

}

class BlockingQueueData {

    private volatile boolean flag = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    public BlockingQueueData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws InterruptedException {
        String data = null;
        while (flag) {
            data = atomicInteger.incrementAndGet() + " ";
            boolean retValue = blockingQueue.offer(data, 2l, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列成功-->"+data);
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列失败->"+data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t生产结束");
    }

    public void comsume() throws InterruptedException {
        String data = null;
        while (flag) {
            data = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if (data!=null){
                System.out.println(Thread.currentThread().getName()+"\t消费队列成功->"+data);
            }else {
                System.out.println(Thread.currentThread().getName()+"\t消费队列失败");
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t消费结束");
    }

    public void stop(){
        flag = false;
        System.out.println(Thread.currentThread().getName()+"\t 结束");
    }

}



