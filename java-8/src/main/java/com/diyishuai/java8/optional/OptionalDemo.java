package com.diyishuai.java8.optional;

import com.diyishuai.java8.Student;

import java.util.Optional;

/**
 * @author Shea
 * @date 2021-01-20
 * @description
 */
public class OptionalDemo {

    private static boolean isNull = false;

    public static void main(String[] args) {
        String id = "666";

        //1.7
        Student student = getById(id);
        if (student != null) {
            System.out.println("if : name -> " + student.getName());
        }

        //1.8
        Optional<Student> studentOpt = Optional.ofNullable(getById(id));
        studentOpt.ifPresent(s ->
                System.out.println("opt : name -> " + s.getName())
        );
    }

    private static Student getById(String id) {
        return isNull ? null : new Student().setId(id).setName(id + " student");
    }


}
