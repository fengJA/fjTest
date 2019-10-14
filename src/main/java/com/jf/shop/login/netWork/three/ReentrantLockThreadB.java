package com.jf.shop.login.netWork.three;

public class ReentrantLockThreadB extends Thread{
    private ReentrantLockThread lockThread;

    public ReentrantLockThreadB(ReentrantLockThread lockThread) {
        this.lockThread = lockThread;
    }

    @Override
    public void run() {
        lockThread.methodB();
    }
}
