package com.di1shuai.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author shea
 * @since 2022/11/24
 */
public class InternetCase {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress.getHostAddress());
        // 输出
        // 14.215.177.38
    }

}
