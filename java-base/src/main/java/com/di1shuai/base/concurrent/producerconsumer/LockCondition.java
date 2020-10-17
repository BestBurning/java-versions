package com.di1shuai.base.concurrent.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bruce
 * @date: 2019-10-24
 * @description:
 *
 * Lock相当于Synchonized可以精确唤醒
 *
 * X --> Y --> Z
 * ^           |
 * |___________|
 */
public class LockCondition {

    public static void main(String[] args) {
        ConditionData data = new ConditionData();
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {
                    data.x();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"A-"+i).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {
                    data.y();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"B-"+i).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {
                    data.z();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"C-"+i).start();
        }

    }

}

class ConditionData {

    private String data = "X";

    private Lock lock = new ReentrantLock();

    private Condition cX = lock.newCondition();
    private Condition cY = lock.newCondition();
    private Condition cZ = lock.newCondition();

    public void x() throws InterruptedException {
        coditionXYZ("X", "Y", cX, cY);
    }

    public void y() throws InterruptedException {
        coditionXYZ("Y", "Z", cY, cZ);
    }

    public void z() throws InterruptedException {
        coditionXYZ("Z", "X", cZ, cX);
    }

    private void coditionXYZ(String from, String to, Condition cAwait, Condition cSignal) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (!data.equals(from)) {
                cAwait.await();
            }
            //do
            data = to;
            System.out.println(Thread.currentThread().getName() +"\t"+ from+ " -> " + to);
            //通知
            cSignal.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
