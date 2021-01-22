package com.di1shuai.java12.stream;

import com.di1shuai.java12.Student;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shea
 * @date 2021-01-22
 * @description T 操作  合并两个
 */
public class TeeingDemo {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("shea", 28),
                new Student("abel", 25),
                new Student("sean", 40)
        );
        String collect = students.stream().collect(Collectors.teeing(
                Collectors.averagingInt(Student::getAge),
                Collectors.summingInt(Student::getAge),
                (avgAge, sumAge) -> avgAge + " : " + sumAge
        ));
        System.out.println(collect);


    }

}
