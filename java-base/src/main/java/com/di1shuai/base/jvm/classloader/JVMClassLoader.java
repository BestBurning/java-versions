package com.di1shuai.base.jvm.classloader;

/**
 * @author: shea
 * @date: 2021/7/28
 * @description:
 */
public class JVMClassLoader {

    public static void main(String[] args) {
        // null -> Bootstrap
        // 为什么为Null，因为Bootstrap使用C++写的，在Java中没有与之相对应的类，所以返回了空值
        System.out.println(String.class.getClassLoader()); 
        
        //sun.misc.Launcher$ExtClassLoader@3d4eac69 -> Extention
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader()); 
        
        //sun.misc.Launcher$AppClassLoader@6d06d69c -> App
        System.out.println(JVMClassLoader.class.getClassLoader());
        
        
        
        //双亲委派
        System.out.println("===================");
        // null -> Bootstrap
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader()); 
        // null -> Bootstrap
        System.out.println(JVMClassLoader.class.getClassLoader().getClass().getClassLoader());
        
        // app -> extention -> bootstrap
        System.out.println(JVMClassLoader.class.getClassLoader().getParent());
        System.out.println(JVMClassLoader.class.getClassLoader().getParent().getParent());

        // 范围
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));


    }

}
