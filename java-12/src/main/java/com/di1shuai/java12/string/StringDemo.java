package com.di1shuai.java12.string;

import java.util.List;

/**
 * @author Shea
 * @date 2021-01-22
 * @description API更新
 * <p>
 * transform 字符串转换，可以配合函数式接口Function一起使用
 * indent 缩进，每行开头增加空格space和移除空格
 */
public class StringDemo {

    public static void main(String[] args) {
        //transform
        List.of("    Hello    ", "   Hi   ").forEach(
                s -> {
                    String newStr = s.transform(String::strip)
                            .transform(e -> e + " Java 12");
                    System.out.println(newStr);
                });

        //indent
        String result = "Hello\nJava\n12\n".indent(3);
        System.out.println(result);

    }

}
