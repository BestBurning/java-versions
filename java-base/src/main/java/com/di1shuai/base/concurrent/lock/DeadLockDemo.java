package com.di1shuai.base.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-26
 * @description:
 *
 *  jps -l
 *          22340 sun.tools.jps.Jps
 *          692 org.jetbrains.idea.maven.server.RemoteMavenServer
 *          17064
 *          18264 com.diyishuai.lock.DeadLockDemo
 *          18204 org.jetbrains.jps.cmdline.Launcher
 *
 * jstack 18264
 *          "T2":
 *                  at com.diyishuai.lock.DeadLock.run(DeadLockDemo.java:37)
 *                  - waiting to lock <0x000000076b17bf18> (a java.lang.String)
 *                  - locked <0x000000076b17bf50> (a java.lang.String)
 *                  at java.lang.Thread.run(Thread.java:748)
 *          "T1":
 *                  at com.diyishuai.lock.DeadLock.run(DeadLockDemo.java:37)
 *                  - waiting to lock <0x000000076b17bf50> (a java.lang.String)
 *                  - locked <0x000000076b17bf18> (a java.lang.String)
 *                  at java.lang.Thread.run(Thread.java:748)
 *
 *          Found 1 deadlock.
 *
 */
public class DeadLockDemo {


    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLock(lockA,lockB),"T1").start();
        new Thread(new DeadLock(lockB,lockA),"T2").start();

    }

}

class DeadLock implements Runnable {
    private String lock1;
    private String lock2;

    public DeadLock(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"\t"+lock1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "\t" + lock2);
            }
        }
    }


}
