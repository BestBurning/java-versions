package com.di1shuai.java14.record;

/**
 * @author Shea
 * @date 2021-01-22
 * @description
 *
 *
 */
public class RecordDemo{

    public static void main(String[] args) {
        Student s = new Student("shea",18);
        System.out.println(
                """
                getName  -> %s        
                getAge   -> %s
                toString -> %s
                hashCode -> %s        
                """.formatted(
                        s.name(),
                        s.age(),
                        s.toString(),
                        s.hashCode()
                )
        );
    }


}

/**
 * 构造方法
 * hashCode() 方法
 * euqals() 方法
 * toString() 方法
 * 类对象被final 关键字修饰，不能被继承。
 */
record Student(String name,int age){

}