package com.di1shuai.base.concurrent.thread;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bruce
 * @date: 2019-10-25
 * @description:
 *
 *       extendsThread
 *       implementsRunable
 *       implementsCallable
 */
public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        extendsThread();
        implementsRunable();
        implementsCallable();

    }

    private static void implementsCallable() throws ExecutionException, InterruptedException {
        //A
        FutureTask futureTaskA = new FutureTask<String>(new ImplementsCallable());
        new Thread(futureTaskA,"implementsCallable-A").start();

        //B
        Callable<String> callable  = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return testString();
            }
        };
        FutureTask futureTaskB = new FutureTask<String>(callable);
        new Thread(futureTaskB,"implementsCallable-B").start();


        //C
        FutureTask<String> futureTaskC = new FutureTask<String>(()->{
            TimeUnit.SECONDS.sleep(5);
            return testString();
        });
        new Thread(futureTaskC,"implementsCallable-C").start();
        //B sleep 3   A sleep 1     但是由于B的get阻塞，导致A只能够出现在B后面
        System.out.println(Thread.currentThread().getName()+"\t implementsCallable get B(sleep 3 ) -> "+futureTaskB.get());
        System.out.println(Thread.currentThread().getName()+"\t implementsCallable get A(sleep 1 ) -> "+futureTaskA.get());
        System.out.println(Thread.currentThread().getName()+"\t implementsCallable get C(sleep 5 ) -> "+futureTaskC.get());
    }

    private static void extendsThread() {
        //A
        new ExtendsThread("继承Thread-A").start();

        //B
        new Thread("继承Thread-B"){
            @Override
            public void run() {
                test();
            }
        }.start();
    }
    private static void implementsRunable() {
        //A
        new Thread(new ImplementsRunable(),"ImplementsRunable-A").start();

        //B
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                test();
            }
        };
        new Thread(runnable,"ImplementsRunable-B").start();

        //C
        new Thread(()->{
           test();
        },"ImplementsRunable-C").start();
    }

    /**
     * 测试代码
     */
    public static void test(){
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName()+" -> "+i);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 测试代码
     */
    public static String testString(){
       return UUID.randomUUID().toString().substring(0,8);
    }
}

class ImplementsCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return ThreadDemo.testString();
    }
}

class ImplementsRunable implements Runnable{

    @Override
    public void run() {
       ThreadDemo.test();
    }
}

class ExtendsThread extends Thread{

    @Override
    public void run() {
        ThreadDemo.test();
    }

    public ExtendsThread() {
        super();
    }

    public ExtendsThread(String name) {
        super(name);
    }
}