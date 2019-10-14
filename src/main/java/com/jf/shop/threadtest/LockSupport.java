package com.jf.shop.threadtest;

/**
 * @author fengj
 * @date 2019/10/7 -21:39
 */
public class LockSupport {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("t3.....");

        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("t1.....");
                java.util.concurrent.locks.LockSupport.park(); // 表示暂停线程，释放锁
                System.out.println("t2.......");
            }
        };

        t.start();
        System.out.println("t4......");
        Thread.sleep(1000);//表示主线程暂停1秒

        java.util.concurrent.locks.LockSupport.unpark(t);// 叫醒线程
    }



}
