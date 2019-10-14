package com.jf.shop.login.netWork.three;

import javax.xml.bind.DatatypeConverter;

public class CallbackThreadTest {
    //回调方法
    public static void reciveDigst(byte[] digest, String fileName){
        StringBuilder sb = new StringBuilder(fileName);
        sb.append(": ");
        sb.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(sb);
    }

    public static void main(String[] args) {
        for (String fileName : args){
            CallbackThread ct = new CallbackThread(fileName);
            Thread t = new Thread(ct);
            t.setDaemon(true);
            t.start();
        }
    }
}
