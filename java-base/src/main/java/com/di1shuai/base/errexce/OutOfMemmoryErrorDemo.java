package com.di1shuai.base.errexce;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Bruce
 * @date: 2019-11-12
 * @description:
 */
public class OutOfMemmoryErrorDemo {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        byte[] bytes1 = new byte[1024 * 1024 * 1024 * 1024];
        byte[] bytes2 = new byte[1024 * 1024 * 1024 * 1024];
        byte[] bytes3 = new byte[1024 * 1024 * 1024 * 1024];
        byte[] bytes4 = new byte[1024 * 1024 * 1024 * 1024];
        byte[] bytes5 = new byte[1024 * 1024 * 1024 * 1024];
        list.add(bytes1);
        list.add(bytes2);
        list.add(bytes3);
        list.add(bytes4);
        list.add(bytes5);
    }



}
