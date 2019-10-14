package com.jf.shop.login.netWork.three;

public class ReentrantLockThreadA extends Thread{
    private ReentrantLockThread lockThread;

    public ReentrantLockThreadA(ReentrantLockThread lockThread) {
        this.lockThread = lockThread;
    }
    @Override
    public void run() {
        lockThread.methodA();
    }
}
