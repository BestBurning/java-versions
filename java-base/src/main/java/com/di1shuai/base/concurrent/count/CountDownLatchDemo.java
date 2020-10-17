package com.di1shuai.base.concurrent.count;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-22
 * @description:
 *
 * 计数器CountDownLatch
 * 一般用于多个线程同时完成任务，全部完成时才进行后续操作
 * 所以，初始化赋值为线程数，每个线程完成任务后-1
 * countDownLatch.countDown()
 * 当计数器为0时，主线程解除等待
 * countDownLatch.await()
 *
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        demo1();
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("===========");
        demo2();

    }

    private static void demo2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {

                CountDown countDown = CountDown.getCountDown(tmp);
                System.out.println(Thread.currentThread().getName() + "->操作>\t" + countDown + "|\t" + countDown.getCode() + "|\t" + countDown.getName() + "\t<正在执行");
                try {
                    TimeUnit.SECONDS.sleep(countDown.getTimeSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "->操作>\t" + countDown + "|\t" + countDown.getCode() + "|\t" + countDown.getName() + "\t>执行完毕");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "->执行完毕");
    }

    private static void demo1() {
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "->操作" + tmp + "执行");
            }, String.valueOf(i)).start();
        }
        System.out.println("执行完毕");
    }


}

enum CountDown {
    ONE(1, "关灯", 1),
    TOW(2, "关窗", 2),
    THREE(3, "关电视", 2),
    FOUR(4, "打开监控", 9),
    FIVE(5, "关门", 5);

    @Getter
    int code;
    @Getter
    String name;
    @Getter
    int timeSeconds;

    CountDown(int code, String name, int timeSeconds) {
        this.code = code;
        this.name = name;
        this.timeSeconds = timeSeconds;
    }

    public static CountDown getCountDown(int code) {
        CountDown[] values = CountDown.values();
        for (CountDown countDown : values) {
            if (countDown.getCode() == code) {
                return countDown;
            }
        }
        return null;
    }

}
