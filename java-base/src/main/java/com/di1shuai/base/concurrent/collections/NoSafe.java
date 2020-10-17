package com.di1shuai.base.concurrent.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: Bruce
 * @date: 2019-10-21
 * @description:
 * 不安全集合
 *      ArrayList
 *      HashSet
 *      HashMap
 *
 * 在多线程环境下，会发生ConcurrentModificationException
 * 解决方案：
 *      1 使用安全的集合： Vector
 *      2 使用工具类Collections的synchonizedXXX方法，在不安全集合外层包一层
 *      3 使用java.util.concurrent包下的
 *             CopyOnWriteArrayList 写时复制，读写分离
 *             CopyOnWriteArraySet，底层为CopyOnWriteArrayList
 *             ConcurrentHashMap
 */
public class NoSafe {

    public static void main(String[] args) {
//        arrayList(3);
//        arrayListCME();
//        useVector();
//        useCollections();
//        useCopyOnWrite();


//        hashSet();
//        hashSetUseCollections();
//        hashSetUseCopyOnWrite();

//        hashMap();
//        hashMapUseCollections();
        hashMapUseConcurrentHashMap();
    }

    private static void hashMapUseConcurrentHashMap() {
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void hashMapUseCollections() {
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }


    private static void hashMap() {
        Map<String,String> map = new HashMap<String,String>();

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void hashSetUseCopyOnWrite() {
        Set<String> set = new CopyOnWriteArraySet();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void hashSetUseCollections() {
        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void hashSet() {
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 写时复制，读写分离的思想
     */
    private static void useCopyOnWrite() {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    private static void useCollections() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    private static void useVector() {
        List<String> list = new Vector<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * java.util.ConcurrentModificationException
     *
     *
     */
    private static void arrayListCME() {
       arrayList(30);
    }

    private static void arrayList(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }


}
