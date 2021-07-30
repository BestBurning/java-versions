package com.di1shuai.base.jvm.classloader;

import java.io.*;

/**
 * @author: shea
 * @date: 2021/7/29
 * @description: classloader应用：
 * 加密classloader
 * <p>
 *     x ^ y ^ y = x
 * 生成class文件后进行加密
 * 使用解密的classloader进行读取
 */
public class EncriptionClassLoader extends ClassLoader {


    private static final int seed = 0B10110110;

    private static final String basepath = "/Users/shea/Documents/GitRepo/mine/back-end/java-versions/java-8/target/classes/";
//    String basepath = "/Users/shea/Documents/GitRepo/mine/back-end/java-versions/java-base/target/classes";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("======进入自定义ClassLoader - findClass =====");
        File file = new File(basepath, name.replaceAll("\\.", "/").concat(".di1shuaiclass"));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(b ^ seed);
            }
            fileInputStream.close();
            byteArrayOutputStream.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("======结束自定义ClassLoader - findClass=====");
        }
        return super.findClass(name);
    }


    public static void main(String[] args) throws Exception {
//        String className = "com.di1shuai.base.gc.HelloJVM";
        String className = "com.diyishuai.java8.Student";
        encFile(className);
        EncriptionClassLoader encriptionClassLoader = new EncriptionClassLoader();
        Class<?> aClass = encriptionClassLoader.loadClass(className);
        System.out.println(aClass);
    }

    private static void encFile(String name) throws Exception {
        File file = new File(basepath, name.replaceAll("\\.", "/").concat(".class"));
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(basepath, name.replaceAll("\\.", "/").concat(".di1shuaiclass")));
        int b = 0;
        while ((b = fileInputStream.read()) != -1) {
            fileOutputStream.write(b ^ seed);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }


}
