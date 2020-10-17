package com.di1shuai.base.concurrent.future;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class Main {
    public static void main(String[] args) {
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功！");
        System.out.println("做其他的事情");

        String result = data.getRequest();
        System.out.println(result);
    }

}
