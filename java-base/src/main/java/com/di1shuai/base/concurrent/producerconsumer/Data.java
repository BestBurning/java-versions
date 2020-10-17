package com.di1shuai.base.concurrent.producerconsumer;

/**
 * @author: Bruce
 * @date: 2019-10-24
 * @description:
 */
public interface Data {

    void increment() throws InterruptedException;

    void decrement() throws InterruptedException;

    default void producer(int threadNumber,int fori){
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= fori; j++) {
                        this.increment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"P-"+i).start();
        }
    }
    default void consumer(int threadNumber,int fori){
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= fori; j++) {
                        this.decrement();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"C-"+i).start();
        }
    }

}



