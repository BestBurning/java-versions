package com.di1shuai.java9.stream;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Shea
 * @date 2021-01-21
 * @description Stream API 更新
 * `takeWhile()`  返回第一次遇到false之前的元素
 * `dropWhile()`  返回第一次遇到false之后的元素
 * `iterate`
 * `ofNullable`
 */

public class StreamAPI {

    public static void main(String[] args) {
        Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(1, 2, 3, 4, 5);

        System.out.println("source");
        streamSupplier.get().forEach(System.out::println);

        System.out.println("takeWhile");
        streamSupplier.get()
                .takeWhile(x -> x < 3)
                .forEach(System.out::println);

        System.out.println("dropWhile");
        streamSupplier.get()
                .dropWhile(x -> x < 3)
                .forEach(System.out::println);

        System.out.println("iterate");
        IntStream.iterate(2, x -> x < 10000, x -> x * x).forEach(System.out::println);


        System.out.println("ofNullable");
        Stream.ofNullable(100).forEach(System.out::println);
        Stream.ofNullable(null).forEach(System.out::println);

        System.out.println();
    }

}
