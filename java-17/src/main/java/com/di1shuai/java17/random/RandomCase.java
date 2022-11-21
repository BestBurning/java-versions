package com.di1shuai.java17.random;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * @author shea
 * @since 2022/11/21
 */
public class RandomCase {


    public static void main(String[] args){
        RandomGeneratorFactory.all().forEach(e -> System.out.println(e.group() + "-" + e.name()));
        //生成10个10以内的随机数
        RandomGeneratorFactory<RandomGenerator> L128X1024MixRandom = RandomGeneratorFactory.of("L128X1024MixRandom");
        RandomGenerator randomGenerator = L128X1024MixRandom.create(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            System.out.println(randomGenerator.nextInt(10));
        }
    }



}
