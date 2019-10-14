package com.jf.shop.login.netWork.three;

public class SleepThread extends Thread{
    public static void main(String[] args) {
        Thread th1 = new Thread(){
            @Override
            public void run(){
                System.out.println("睡觉");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("被吵醒");
                }
                System.out.println("不睡了");
            }
        };
        Thread th2 = new Thread(){
            @Override
            public void run(){
                System.out.println("工作");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("第"+i+"下");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("收工了");
                System.out.println("调用th1的interrupt方法");
                th1.interrupt();
            }
        };
        th1.start();
        th2.start();
    }
}
