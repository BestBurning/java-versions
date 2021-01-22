package com.di1shuai.java11.string;

/**
 * @author Shea
 * @date 2021-01-22
 * @description String API 更新
 * <p>
 * isBlank() 判空。
 * strip() 去除首尾空格
 * stripLeading() 去除字符串首部空格
 * stripTrailing() 去除字符串尾部空格
 * lines() 分割获取字符串流。
 * repeat() 复制字符串
 */
public class StringDemo {

    public static void main(String[] args) {

        // 判空
        System.out.println(
                "isBlank() ->" +
                        "".isBlank() + "\t" +    // true
                        "   ".isBlank() + "\t" + // true
                        "1".isBlank() + "\t"    // false
        );
        // 去除首尾空格
        System.out.println("strip() ->" + "Java11 ".strip()); //"Java11"
        // 去除首部空格
        System.out.println("stripLeading() ->" + " Java11 ".stripLeading());   // "Java11 "
        // 去除字符串尾部空格
        System.out.println("stripTrailing() ->"+" Java11 ".stripTrailing());   // " Java11"
        // 行数统计
        System.out.println("lines() ->"+"a\nb\nc".lines().count());    // 3
        // 复制字符串
        System.out.println("repeat(3) ->"+"Java11".repeat(3));   // "Java11Java11Java11"
    }

}
