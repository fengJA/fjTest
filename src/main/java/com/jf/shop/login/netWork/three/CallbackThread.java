package com.jf.shop.login.netWork.three;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackThread implements Runnable {
    private String fileName;


    public CallbackThread(String fileName) {
        this.fileName = fileName;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream(fileName), messageDigest);
            while (digestInputStream.read() != -1);
            digestInputStream.close();
            byte[] digist = messageDigest.digest();
            //在线程的最后调用主线程的一个静态方法，表示告诉主线程盖线程已经执行完毕，该类是进行回调的类，必须有回调对象的一个引用
            CallbackThreadTest.reciveDigst(digist, fileName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
