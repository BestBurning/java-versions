package com.di1shuai.java19.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author shea
 * @since 2022/11/24
 */
//使用模式 默认是Mode.Throughput
@BenchmarkMode(Mode.AverageTime)
// 配置预热次数，默认是每次运行1秒，运行10次，这里设置为3次
@Warmup(iterations = 3, time = 1)
// 本例是一次运行4秒，总共运行3次，在性能对比时候，采用默认1秒即可
@Measurement(iterations = 3, time = 4)
// 配置同时起多少个线程执行
@Threads(1)
//代表启动多个单独的进程分别测试每个方法，这里指定为每个方法启动一个进程
@Fork(1)
// 定义类实例的生命周期，Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能
@State(value = Scope.Benchmark)
// 统计结果的时间单元
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Performance19 {

    @Param(value = {"10", "50", "100"})
    private int length;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Performance19.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }


    @Benchmark
    public void testStringBufferAdd(Blackhole blackhole) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

}
