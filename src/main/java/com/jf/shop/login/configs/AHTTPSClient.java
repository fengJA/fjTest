package com.jf.shop.login.configs;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class AHTTPSClient {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("cant not find the address");
            return;
        }
        String host = args[0];
        int port = 8889;
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;
        try {
            socket = (SSLSocket)factory.createSocket(host,port);
            //启用所有密码组
            String[] supported = socket.getEnabledCipherSuites();
            socket.setEnabledCipherSuites(supported);

            Writer out = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            out.write("GET http://" + host + "/ HTTP1.1\r\n");
            out.write("HOST" + host + "\r\n");
            out.write("\r\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //得到首部
            String s;
            while (!(s = in.readLine()).equals("")){
                System.out.println(s);
            }

            //获取第一行
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;
            try {
                //得到的length是16进制
                length = Integer.parseInt(contentLength.trim(),16);

            } catch (NumberFormatException e) {
                //该服务器没有在响应的第一行发送Content-length
            }

            System.out.println(contentLength);

            int c;
            int i = 0;
            while ((c = in.read()) != -1 && i++ < length){
                System.out.println(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
