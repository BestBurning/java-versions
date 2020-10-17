package com.di1shuai.base.concurrent.masterworker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class Master {

    // 任务容器
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();
    // worker容器
    private HashMap<String,Thread> workerMap = new HashMap<String, Thread>();
    // 结果集容器
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String, Object>();

    public Master(int workerCount) {

        for (int i = 0; i < workerCount; i++) {
            Worker worker = new Worker(taskQueue,resultMap);
            workerMap.put("worker-"+i,new Thread(worker));
        }
    }

    public void exec(){
        for (Map.Entry<String,Thread> e :workerMap.entrySet()) {
            e.getValue().start();
        }
    }

    public void submit(Task task){
        taskQueue.add(task);
    }
    public void submit(List<Task> tasks){
        tasks.stream().forEach(task -> taskQueue.add(task));

    }

    public ConcurrentHashMap<String,Object> getResult(){
        return resultMap;
    }

    public boolean isComplete() {
        for (Map.Entry<String,Thread> e :workerMap.entrySet()) {
            if (e.getValue().getState() != Thread.State.TERMINATED)
                return false;
        }
        return true;
    }
}
