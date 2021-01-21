package com.di1shuai.java9.optional;

import java.util.Optional;

/**
 * @author Shea
 * @date 2021-01-21
 * @description Optional 改进
 * stream()
 * ifPresentOrElse()
 * or()
 */
public class OptionalDemo {


    public static void main(String[] args) {
        Optional<Integer> optional = Optional.of(1);
        optional.ifPresentOrElse(
                x -> System.out.println("Value: " + x),
                () -> System.out.println("Not Present.")
        );

        optional = Optional.empty();

        optional.ifPresentOrElse(
                x -> System.out.println("Value: " + x),
                () -> System.out.println("Not Present.")
        );
    }

}
