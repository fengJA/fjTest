package com.jf.shop.login.netWork.three;

public class CallbackInstanceThreadTest {
    private String fileName;
    byte[] digest;

    public CallbackInstanceThreadTest(String fileName) {
        this.fileName = fileName;
    }

    public void receiveDigest(byte[] digest){
        this.digest = digest;
        System.out.println(this);
    }

    public void callbackThread(){
        CallbackInstanceThread cit = new CallbackInstanceThread(fileName, this);
        Thread t = new Thread(cit);
        t.start();
    }

    public static void main(String[] args) {
        for (String fileName:args){
            CallbackInstanceThreadTest ct = new CallbackInstanceThreadTest(fileName);
            ct.callbackThread();
        }
    }
}
