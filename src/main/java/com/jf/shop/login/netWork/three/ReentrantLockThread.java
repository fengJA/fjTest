package com.jf.shop.login.netWork.three;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockThread {
    private Lock lock = new ReentrantLock();

    public void methodA(){
        lock.lock();
        try {
            System.out.println("methodA:"+Thread.currentThread().getName());
            Thread.sleep(6000);
            System.out.println("methodA end:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void methodB(){
        lock.lock();
        try {
            System.out.println("methodB:"+Thread.currentThread().getName());
            System.out.println("methodB:"+Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
