package com.jf.shop.login.netWork.three;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnThread implements Runnable {
    private String fileName;
    private byte[] digist;

    public ReturnThread(String fileName) {
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
           digist = messageDigest.digest();
//           Thread.sleep(312);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getDigist(){
        return digist;
    }

}
