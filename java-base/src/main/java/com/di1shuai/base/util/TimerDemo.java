package com.di1shuai.base.util;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: Shea
 * @date: 2020/7/25
 * @description:
 */
public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("延时执行" + LocalDateTime.now());
            }
        }, 1500);
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("间隔执行"+LocalDateTime.now());
            }
        }, 1000, 1000);


    }

}
