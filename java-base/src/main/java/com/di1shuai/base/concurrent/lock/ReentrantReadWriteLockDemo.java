package com.di1shuai.base.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Bruce
 * @date: 2019-10-22
 * @description: 读写锁
 * <p>
 * 读 共享
 * 写 不共享
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        Cache cache = new Cache1();
        demo(cache);
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("====================");

        Cache cacherw = new CacheReadWrite();
        demo(cacherw);


    }

    private static void demo(Cache cache) {
        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                cache.set(tmp + "", tmp + " ");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                cache.get(tmp+"");
            }, String.valueOf(i)).start();
        }
    }

}

interface Cache {

    void set(String key, String value);

    String get(String key);

    void clear();

}


class Cache1 implements Cache {

    private volatile Map<String, String> map = new HashMap();

    @Override
    public void set(String key, String value) {
        System.out.println(Thread.currentThread().getName() + " 正在写入");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入完成");
    }

    @Override
    public String get(String key) {
        System.out.println(Thread.currentThread().getName() + " 正在读取");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取完成->"+value);
        return value;
    }

    @Override
    public void clear() {
        map.clear();
    }

}

class CacheReadWrite implements Cache {

    private volatile Map<String, String> map = new HashMap();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void set(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入");
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public String get(String key) {
        lock.readLock().lock();
        String value = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取");
            TimeUnit.MILLISECONDS.sleep(300);
            value = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成->"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

    @Override
    public void clear() {
        map.clear();
    }

}
