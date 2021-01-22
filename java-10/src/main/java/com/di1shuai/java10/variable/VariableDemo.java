package com.di1shuai.java10.variable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shea
 * @date 2021-01-21
 * @description 局部参数类型推断
 * 适用于
 * 具有初始化器的局部变量
 * 增强型for循环中的索引变量
 * 传统for循环中声明的局部变量
 *
 * 不适用于
 * 推断方法的参数类型
 * 构造函数参数类型推断
 * 推断方法返回类型
 * 字段类型推断
 * 捕获表达式
 *
 */
public class VariableDemo {

    public static void main(String[] args) {
        // Before 10
        String str = "hello ";
        Integer i = 1;
        System.out.println(str + i);

        // After 10
        var obj1 = "hi ";
        var obj2 = Integer.parseInt("1");
        System.out.println(obj1 + obj2);
        System.out.println("obj1 -> " + obj1.getClass().getSimpleName());
        System.out.println("obj2 -> " + obj2);
    }

}
