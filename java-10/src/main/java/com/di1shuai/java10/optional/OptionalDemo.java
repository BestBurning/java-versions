package com.di1shuai.java10.optional;

import java.util.Optional;

/**
 * @author Shea
 * @date 2021-01-22
 * @description orElseThrow 没有值时抛异常
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional<String> op;
        try {
            op = Optional.of(null);
            op.orElseThrow();
        } catch (RuntimeException e) {
            System.out.println("捕获到");
        }
        op = Optional.of("hi");
        op.orElseThrow();
        System.out.println(op.get());

    }

}
