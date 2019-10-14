package com.jf.shop.login.netWork.three;

public class StaticSynchronizedThreadTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                StaticSynchronizedThread.methodA();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                StaticSynchronizedThread.methodB();
            }
        };

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }


}
