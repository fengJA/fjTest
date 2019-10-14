package com.jf.shop.login.netWork.three;

public class ReentrantLockThreadTest {
    public static void main(String[] args) {
        ReentrantLockThread thread = new ReentrantLockThread();
        ReentrantLockThreadA threadA = new ReentrantLockThreadA(thread);
        ReentrantLockThreadB threadB = new ReentrantLockThreadB(thread);
        threadA.start();
        threadB.start();
    }
}
