package com.di1shuai.base.concurrent.future;

/**
 * @author Bruce
 * @date 16/9/8
 */
public class FutureData implements Data{

    private RealData realDate;

    private boolean isReady=false;

    public FutureData() {
    }

    public synchronized void setRealDate(RealData realDate) {
       //如果已经装在完毕就是直接返回
        if (isReady){
            return;
        }
        //如果没装载，进行装载真实对象
        this.realDate=realDate;
        isReady=true;
        notify();
    }

    @Override
    public synchronized String getRequest() {
        //如果没装载好 程序就一直处于阻塞状态
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果装载好直接获取数据即可
        return this.realDate.getRequest();
    }
}
