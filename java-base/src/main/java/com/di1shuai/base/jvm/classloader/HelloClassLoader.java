package com.di1shuai.base.jvm.classloader;

import java.io.*;

/**
 * @author: shea
 * @date: 2021/7/29
 * @description: 自定义类加载器
 * 指定文件路径进行加载
 */
public class HelloClassLoader extends ClassLoader {

    String basepath = "/Users/shea/Documents/GitRepo/mine/back-end/java-versions/java-8/target/classes/";
//    String basepath = "/Users/shea/Documents/GitRepo/mine/back-end/java-versions/java-base/target/classes";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("======进入自定义ClassLoader - findClass =====");
        File file = new File(basepath, name.replaceAll("\\.", "/").concat(".class"));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
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


    public static void main(String[] args) throws ClassNotFoundException {
//        String className = "com.di1shuai.base.gc.HelloJVM";
        String className = "com.diyishuai.java8.Student";

        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> aClass = helloClassLoader.loadClass(className);
        System.out.println(aClass);


    }

}
