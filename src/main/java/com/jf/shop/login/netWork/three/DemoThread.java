package com.jf.shop.login.netWork.three;

public class DemoThread {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i=0;i<5;i++) {
                    System.out.println("t1线程开始执行");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1线程准备结束");
                System.out.println("t1线程结束执行");//当t1线程结束时，守护线程t2也会跟着t1结束
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("t2线程正在执行");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.setDaemon(true);//将线程设置为守护线程
        t2.start();
    }

}
