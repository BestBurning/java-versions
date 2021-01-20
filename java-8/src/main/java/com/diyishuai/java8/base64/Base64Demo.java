package com.diyishuai.java8.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Shea
 * @date 2021-01-20
 * @description
 */
public class Base64Demo {

    private static Base64.Encoder encoder = Base64.getEncoder();

    private static Base64.Decoder decoder = Base64.getDecoder();

    public static void main(String[] args) {
        String str = "Hello Java8, 你好 Java8";
        System.out.println("原字符串 -> " + str);

        String encode = encode(str);
        System.out.println("Base64 Encode -> " + encode);

        String decode = decode(encode);
        System.out.println("Base64 Decode -> " + decode);
    }

    public static String decode(String encode) {
        return new String(decoder.decode(encode),StandardCharsets.UTF_8);
    }

    public static String encode(String str) {
        return encoder.encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }


}
