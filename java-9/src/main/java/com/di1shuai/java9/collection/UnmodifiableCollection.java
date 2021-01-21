package com.di1shuai.java9.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Shea
 * @date 2021-01-21
 * @description 不可变集合
 */
public class UnmodifiableCollection {

    public static void main(String[] args) {
        // before 9
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello ");
        stringList.add("Java 9");
        List<String> unmodifiableList = Collections.unmodifiableList(stringList);

        // after 9
        List<String> unmodifiableList_9 = List.of("Hello ","Java 9");

        try {
            unmodifiableList.add("hi");
        }catch (UnsupportedOperationException e){
            System.err.println("无法添加");
            e.printStackTrace();
        }

        try {
            unmodifiableList_9.add("hi");
        }catch (UnsupportedOperationException e){
            System.err.println("无法添加");
            e.printStackTrace();
        }

    }

}
