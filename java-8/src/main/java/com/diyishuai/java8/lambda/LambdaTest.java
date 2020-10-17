package com.diyishuai.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bruce on 16/8/8.
 */
public class LambdaTest {


    public static void main(String[] args) {

        System.out.println("foreach-less1.8");
        //foreach-less1.8
        List<String> strings = Arrays.asList("a","b","c","c");
        for (String str:strings) {
            System.out.println(str);
        }
        System.out.println("-------");
        System.out.println("foreach 1.8");
        //foreach 1.8
        strings.forEach(str -> System.out.println(str));
//        strings.forEach(str -> {System.out.println(str);});
        System.out.println("===========");

        //stream
        strings.stream()
                .distinct()
                .limit(2)
                .filter(str -> str == "a")
                .forEach(str -> System.out.println(str));

        System.out.println("===========");
        System.out.println("peek");
        strings.parallelStream()
                .skip(2)
                .forEach(str -> System.out.println(str));
        System.out.println("===========");
        distinctPrimary("12","23","12","23");

        System.out.println("===========");

    }

    public static void distinctPrimary(String...numbers) {
        List<String> strings = Arrays.asList(numbers);
        List<Integer> collect = strings.parallelStream()
                .map(e -> new Integer(e))
//              .filter(e -> Primes.isPrime(e))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}
