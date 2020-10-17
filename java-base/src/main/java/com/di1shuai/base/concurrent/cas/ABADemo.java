package com.di1shuai.base.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: Bruce
 * @date: 2019-10-20
 * @description:
 * ABA问题是在多线程环境下，CAS由于只关注开始和结尾的值，而导致中间的变化被忽略了
 * 即：  时间:     T1     T2        T3         T4
 *      主内存 :   A  ->  B  ->     A     ->   B
 *      线程1 :    A  ->  B  ->     A
 *      线程2 :    A  -> 阻塞 -> CAS(A,B)  ->  B(成功)
 *
 * 所以如果要解决这个问题，可以加入时间戳(版本号)，类似于乐观锁的机制
 * 即：  时间:     T1     T2        T3          T4
 *      主内存 :  A1  ->  B2  ->    A3     ->   A3
 *      线程1 :   A1  -> B2  ->    A3
 *      线程2 :   A1  -> 阻塞 -> CAS(A1,B) ->   A3(失败)
 *
 */
public class ABADemo {

    public static void main(String[] args) {
        ABADemo();
        System.out.println("=================================");
        ABAFix();

    }

    private static void ABAFix() {
        User A = new User("A");
        User B = new User("B");

        AtomicStampedReference<User> atomicReference = new AtomicStampedReference<>(A,1);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " -> "
                    + atomicReference.compareAndSet(A,B,atomicReference.getStamp(),atomicReference.getStamp()+1)
                    + " : "+A+"->" + atomicReference.getReference());
            System.out.println(Thread.currentThread().getName()+ " -> "
                    + atomicReference.compareAndSet(B,A,atomicReference.getStamp(),atomicReference.getStamp()+1)
                    + " : "+B+"->" + atomicReference.getReference());
        },"T1").start();

        new Thread(() -> {
            int stamp = atomicReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " -> " + atomicReference.compareAndSet(A,B,stamp,stamp+1) + " : " +A+"->"+ atomicReference.getReference());
        },"T2").start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("now -> " + atomicReference.getReference());
    }

    private static void ABADemo() {
        User A = new User("A");
        User B = new User("B");

        AtomicReference<User> atomicReference = new AtomicReference<>(A);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+ " -> " + atomicReference.compareAndSet(A,B) + " : "+A+"->" + atomicReference.get());
            System.out.println(Thread.currentThread().getName()+ " -> " + atomicReference.compareAndSet(B,A) + " : "+B+"->" + atomicReference.get());
        },"T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " -> " + atomicReference.compareAndSet(A,B) + " : " +A+"->"+ atomicReference.get());
        },"T2").start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("now -> " + atomicReference.get());
    }

}
