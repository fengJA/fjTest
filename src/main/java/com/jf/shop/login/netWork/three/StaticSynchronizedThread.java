package com.jf.shop.login.netWork.three;

public class StaticSynchronizedThread {

    public synchronized static void methodA(){
        Thread name = Thread.currentThread();
        System.out.println(name.getName()+"正在运行A方法");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name.getName()+"A方法运行完了");
    }
    public static synchronized void methodB(){
        Thread name = Thread.currentThread();
        System.out.println(name.getName()+"正在运行B方法");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name.getName()+"B方法运行完了");
    }
}
