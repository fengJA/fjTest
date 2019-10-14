package com.jf.shop.login.netWork.three;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOThread {

    public static void main(String[] args) {

        File f = new File("");
        try {
            FileInputStream fi = new FileInputStream(f);
            FileChannel fc = fi.getChannel();
            InputStreamReader is = new InputStreamReader(fi);
            BufferedReader br = new BufferedReader(is);
            ByteBuffer bf = ByteBuffer.allocate(1021);
            int read = fc.read(bf);
            byte[] me = new byte[1024];
            if (read != -1){
                bf.put(me);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
