package com.jf.shop.login.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DayTimeClient {
    public static void main(String[] args) {
        String hostName = args.length > 0 ? args[0] : "www.baidu.com";
        Socket socket = null;
        try {
            socket = new Socket("www.baidu.com",80);
            socket.setSoTimeout(1000);
            InputStream inputStream = socket.getInputStream();
            StringBuilder builder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
           for (int read = reader.read();read != -1;read = reader.read()){

               builder.append((char) read);
           }
            System.out.println(builder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
