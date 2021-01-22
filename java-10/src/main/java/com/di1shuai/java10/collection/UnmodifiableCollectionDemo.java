package com.di1shuai.java10.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shea
 * @date 2021-01-21
 * @description 不可变集合更新
 * List、Map、Set
 * 提供静态方法 copyOf
 */
public class UnmodifiableCollectionDemo {

    public static void main(String[] args) {

        var oldList = new ArrayList<String>();
        oldList.add("hello");
        oldList.add("Java10");

        var copyList = List.copyOf(oldList);
        oldList.add("hi");
        try {
            copyList.add("Java10");
        }catch (UnsupportedOperationException e){
            System.out.println("操作不允许");
            e.printStackTrace();
        }
    }

}
