package com.di1shuai.base.concurrent.thread;

import com.di1shuai.base.concurrent.util.StringUtil;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bruce
 * @date: 2019-10-25
 * @description:
 * public ThreadPoolExecutor(
 * int corePoolSize,                     常驻线程
 * int maximumPoolSize,                  最大线程
 *                      这个值请根据具体业务来设置
 *                    - CPU密集型任务：Ncpu+1
 *                          由于任务是CPU密集型，导致执行任务时CPU无法得到休息，则执行任务的速度取决于CPU的个数，如配置Ncpu+1个线程的线程池
 *                          （原因是当计算密集型线程偶尔由于页缺失故障或其他原因而暂停时，这个“额外的”线程也能确保这段时间内的CPU始终周期不会被浪费。）
 *                    - IO密集型任务：
 *                          由于IO密集型任务的线程不是一直在执行（会在IO时阻塞），所以为了充分利用CPU，要配置尽可能多的线程。
 *                          Ncpu/(1-阻塞系数) 阻塞系数在0-1之间，0.8  0.9
 *                    - 混合型任务：要根据任务等待阻塞时间与CPU计算时间的比重来决定线程数量：
 *                                Nthreads​=Ncpu​∗Ucpu​∗(1+W/C​)
 *                                Ncpu代表CPU的个数，Ucpu代表CPU利用率的期望值(0<Ucpu<1)，W/C是等待时间与计算时间的比例。
 *
 * long keepAliveTime,                   多余的空闲线程存活时间
 * TimeUnit unit,                        存活时间单位
 * BlockingQueue<Runnable> workQueue,    工作队列
 * ThreadFactory threadFactory,          threadFactory
 * RejectedExecutionHandler handler      拒绝策略
 * )
 * <p>
 * <p>
 * execute()进行3个步骤：
 * 1.如果正在运行的线程少于corePoolSize线程，请尝试使用给定命令作为其第一个任务来启动新线程。
 * 对addWorker的调用从原子上检查runState和workerCount，从而通过返回false来防止在不应该添加线程的情况下发出虚假警报。
 * 2.如果任务可以成功排队，那么我们仍然需要仔细检查是否应该添加线程（因为现有线程自上次检查后就已死亡）
 * 或自进入此方法以来该池已关闭。因此，我们重新检查状态，并在必要时回滚排队，如果已停止，或者在没有线程的情况下启动新线程。
 * 3.如果我们无法将任务排队，则尝试添加一个新线程。如果失败，
 * 我们知道我们已关闭或已饱和，因此拒绝该任务。
 */
public class ThreadPoolDemo {

    private static Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) {
        System.out.println("JVM可用线程数:" + Runtime.getRuntime().availableProcessors());
        single();
        fix();
        cached();
        threadPoolExecutor_AbortPolicy();
        threadPoolExecutor_CallerRunsPolicy();
        threadPoolExecutor_DiscardOldestPolicy();
        threadPoolExecutor_DiscardPolicy();
    }

    /**
     * AbortPolicy为默认的拒绝策略，直接抛出异常
     */
    private static void threadPoolExecutor_AbortPolicy() {
        try {
            threadPoolExecutor(3, 5, 3, new ThreadPoolExecutor.AbortPolicy(),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CallerRunsPolicy会将被拒绝的任务返回给调用线程，如main线程
     */
    private static void threadPoolExecutor_CallerRunsPolicy() {
        threadPoolExecutor(3, 5, 3, new ThreadPoolExecutor.CallerRunsPolicy(),true);
    }
    /**
     * DiscardPolicy直接丢弃被拒绝的任务
     */
    private static void threadPoolExecutor_DiscardPolicy() {
        threadPoolExecutor(3, 5, 3, new ThreadPoolExecutor.DiscardPolicy(),false);
    }
    /**
     * DiscardOldestPolicy丢弃等待时间最久的请求
     */
    private static void threadPoolExecutor_DiscardOldestPolicy() {
        threadPoolExecutor(
                3,
                5,
                3,
                new ThreadPoolExecutor.DiscardOldestPolicy(),
                false);
    }

    private static void threadPoolExecutor(int core, int max, int queueSize, RejectedExecutionHandler handler,boolean isCount) {
        StringUtil.title("ThreadPoolExecutor: Core-" + core + " Max-" + max + " Queue-" + queueSize + " Handler-" + handler.getClass().getSimpleName());
        do20(new ThreadPoolExecutor(
                        core,
                        max,
                        1l,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(queueSize),
                        Executors.defaultThreadFactory(),
                        handler
                ),
                isCount
        );
    }

    /**
     * public static ExecutorService newSingleThreadExecutor() {
     * return new FinalizableDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()));
     *
     * 由于new LinkedBlockingQueue<Runnable>()的默认大小为Integer.MAX_VALUE,
     * 所以有可能会挤压过多请求而导致OOM
     */
    private static void single() {
        StringUtil.title("Single");
        do20(Executors.newSingleThreadExecutor());
    }

    /**
     * public static ExecutorService newFixedThreadPool(int nThreads) {
     * return new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     * }
     *
     * 由于new LinkedBlockingQueue<Runnable>()的默认大小为Integer.MAX_VALUE,
     * 所以有可能会挤压过多请求而导致OOM
     */
    private static void fix() {
        StringUtil.title("Fix 5");
        do20(Executors.newFixedThreadPool(5));
    }

    /**
     * public static ExecutorService newCachedThreadPool() {
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     *
     *
     * 由于允许的最大线程数为Integer.MAX_VALUE，
     * 可能会创建大量线程而导致OOM
     */
    private static void cached() {
        StringUtil.title("Cached");
        do20(Executors.newCachedThreadPool());
    }

    private static void do20(ExecutorService executorService) {
        //模拟20个用户访问
        doN(executorService,20,true);
    }
    private static void do20(ExecutorService executorService,boolean isCount) {
        //模拟20个用户访问
        doN(executorService,20,isCount);
    }

    private static void doN(ExecutorService executorService,int n,boolean isCount) {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
            AtomicInteger atomicInteger = new AtomicInteger();
            for (int i = 1; i <= n; i++) {
                final int tmp = i;
                executorService.execute(() -> {
                    String name = Thread.currentThread().getName();
                    Integer count = map.get(name);
                    if (isCount)
                        map.put(name, count == null ? 1 : count + 1);
                    atomicInteger.incrementAndGet();
                    System.out.println(name + " -> " + tmp);
                });
            }
            if (isCount){
                while (atomicInteger.intValue() != n) {

                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
                System.out.println("线程数 :" + map.size());
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    System.out.println("线程 \t" + entry.getKey() + " 执行 >" + entry.getValue() + "< 次");
                }
                map.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
