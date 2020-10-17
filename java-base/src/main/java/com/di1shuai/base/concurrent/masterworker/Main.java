package com.di1shuai.base.concurrent.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class Main {

    public static void main(String[] args) {
        // 1 待处理任务List
        List<Task> tasks = new ArrayList();
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setId(i);
            task.setName("task"+i);
            tasks.add(task);
        }
        //2 创建Master
        System.out.println(Runtime.getRuntime().availableProcessors());
//        Master master = new Master(Runtime.getRuntime().availableProcessors());
        Master master = new Master(10);
        //3 提交任务
        master.submit(tasks);
        //4 执行操作
        master.exec();
        //5 查看结果
        long start = System.currentTimeMillis();
        do {

            if (master.isComplete()){
                ConcurrentHashMap<String, Object> result = master.getResult();
                tasks.stream()
                        .forEach(task -> System.out.println("result:"+result.get(task.getId()+"")));
                break;
            }
        } while (true);
        System.out.println(System.currentTimeMillis()-start);
    }

}
