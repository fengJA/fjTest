package com.jf.shop.login.netWork.three;

public class SetPriortyThread {
    public static void main(String[] args) {
        Thread t = Thread .currentThread();
        System.out.println("t1:"+t);
        Thread max = new Thread() {
            @Override
            public void run() {
                Thread t = Thread .currentThread();
                System.out.println("t2:"+t);
                for (int i=0;i<10;i++) {
                    System.out.println("max");
                }
            }
        };
        Thread nor = new Thread() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    System.out.println("nor");
                }
            }
        };
        Thread min = new Thread() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    System.out.println("min");
                }
            }
        };
        long a=System.currentTimeMillis();
        min.setPriority(1);
        nor.setPriority(5);
        max.setPriority(10);
        max.start();
        nor.start();
        min.start();
        long b = System.currentTimeMillis();
        System.out.println((b-a)+"ms");
    }
}
