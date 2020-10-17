package com.di1shuai.base.concurrent.singleton;

/**
 * @author: Bruce
 * @date: 2019-10-19
 * @description:
 */
public class SingletonDemo {

    private volatile static SingletonDemo singleton = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+" > 构造函数");
    }

    /**
     * 不加synchronized 会存在并发问题
     * DCL double check lock 双端锁
     * 双端锁仍然有可能会发生问题，返回的单例有可能是null
     * 不加volatile的话，在singleton = new xxxx 这里，底层会有关键的三个步骤：
     *      1 分配内存空间
     *      2 初始化
     *      3 引用指向内存空间
     *  2，3指令重拍的话，有可能3先2后，未初始化的值为null
     *  所以还是要加volatile防止指令重拍
     * @return
     */
    public static SingletonDemo getInstance(){
        if (singleton == null){
            synchronized (SingletonDemo.class){
                if (singleton == null){
                    singleton = new SingletonDemo();
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                System.out.println(SingletonDemo.getInstance());
            },String.valueOf(i)).start();
        }
    }


}
