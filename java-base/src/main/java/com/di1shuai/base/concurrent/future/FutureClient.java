package com.di1shuai.base.concurrent.future;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class FutureClient {

    public Data request(final String queryStr){
        //1 我想要一个代理对象（Data接口的实现类）先返回个发送请求的客户端，告诉他请求已经接收到，可以做其他的事情了
//        final FutureData futureData = FutureData::new;
        final FutureData futureData = new FutureData();
        //2 启动了一个新的线程，去加载真实的数据，传递给这个代理对象
        Runnable r = () -> {
            RealData realData = new RealData(queryStr);
            futureData.setRealDate(realData);
        };
        new Thread(r).start();

        return futureData;
    }
}
