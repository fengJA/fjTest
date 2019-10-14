package com.jf.shop.login.netWork.three;

public class SynchronizedThread {
    public void buy() {
        Thread t = Thread.currentThread();
        try {
            System.out.println(t.getName()+":正在挑衣服");
            Thread.sleep(5000);
            /*
             * 同步块语法：
             * synchronize(同步监听对象){
             *      需要同步运行的代码
             * }
             * 若希望多个线程同步块中的代码，则必须保证多个线程看到的同步监视器对象是同一个
             * 同步监视器对象可以是Java的任何对象，结合实际情况自行挑选，只要保证多个线程看到的是同一个对象即可
             */
            synchronized (this) {
                System.out.println(t.getName()+":正在试衣服");
                //不会释放同步锁
//                Thread.sleep(5000);
                //
                Thread.yield();
            }

            System.out.println(t.getName()+":结账离开");
        } catch (Exception e) {

        }
    }

}
