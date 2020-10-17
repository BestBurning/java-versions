package com.di1shuai.base.concurrent.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Bruce
 * @date: 2019-10-20
 * @description:
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User u = new User("u");
        User u1 = new User("u1");
        User u2 = new User("u2");

        AtomicReference<User> atomicReference = new AtomicReference<>(u);
        new Thread(() -> {
            System.out.println("u1 : " + atomicReference.compareAndSet(u,u1));
        }).start();
        new Thread(() -> {
            System.out.println("u2 : " + atomicReference.compareAndSet(u,u2));
        }).start();
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("now -> " + atomicReference.get());
    }

}
