package com.di1shuai.java19.virthread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class Jep425Demo {
    public static void firstVirtualThread() {
        // 创建10000个虚拟线程
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }  // try-with-resources，会隐式调用executor.close()
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        firstVirtualThread();
        System.out.printf("firstVirtualThread finished, time cost %d ms\n",
                System.currentTimeMillis() - startTime);
    }


    private static void infoCurrentThread() {
        Thread thread = Thread.currentThread();
        System.out.printf("线程名称: %s，是否虚拟线程: %s\n",
                thread.getName(), thread.isVirtual());
    }

    private static void waysToCreateVirtualThread() {
        // 方式一：直接启动，虚拟线程名称为""
        Thread.startVirtualThread(() -> infoCurrentThread());

        // 方式二：Builder模式构建
        Thread vt = Thread.ofVirtual().allowSetThreadLocals(false)
                .name("VirtualWorker-", 0)
                .inheritInheritableThreadLocals(false)
                .unstarted(() -> infoCurrentThread());
        vt.start();

        // 方式三：Factory模式构建
        ThreadFactory factory = Thread.ofVirtual().allowSetThreadLocals(false)
                .name("VirtualFactoryWorker-", 0)
                .inheritInheritableThreadLocals(false)
                .factory();
        Thread virtualWorker = factory.newThread(() -> infoCurrentThread());
        virtualWorker.start();

        // 方式四：newVirtualThreadPerTaskExecutor
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> infoCurrentThread());
        }

        // 方式五：构建"虚拟线程池"
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);
        executorService.submit(() -> infoCurrentThread());

        infoCurrentThread();
    }

}
