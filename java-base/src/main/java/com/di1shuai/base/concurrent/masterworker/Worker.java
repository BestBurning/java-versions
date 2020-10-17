package com.di1shuai.base.concurrent.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class Worker implements Runnable{

    // 任务容器
    private ConcurrentLinkedQueue<Task> taskQueue;

    // 结果集容器
    private ConcurrentHashMap<String,Object> resultMap;

    public Worker(ConcurrentLinkedQueue<Task> taskQueue, ConcurrentHashMap<String, Object> resultMap) {
        this.taskQueue = taskQueue;
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Task input = taskQueue.poll();
            if (input == null)
                break;
            Object output = handle(input);
            resultMap.put(input.getId()+"",output);
        }
    }

    private Object handle(Task input) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input.getName();
    }
}
