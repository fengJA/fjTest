package com.jf.shop.login.netWork.three;

public class SynchronizedThreadTest {
    public static void main(String[] args) {
        SynchronizedThread shop = new SynchronizedThread();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                shop.buy();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                shop.buy();
            }
        };
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();

    }
}
