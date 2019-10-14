package com.jf.shop.login.chart;/*
package com.jf.shop.login.chart;

import
        java.io.*;
import
        java.net.*;

public class Test{
    //地址使用当前主机测试
    private static String defaultHost = "localhost";
    //服务器端口默认使用12345
    private static int defaultPort = 12345;
    //echo测试的结束标志
    private static final String END_FLAG = "over";
    //用于连接服务器的客户端Socket
    private Socket connection;

    public EchoClient() throws UnknownHostException, IOException {
        //连接服务器服务
        connection = new Socket(defaultHost, defaultPort);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            */
/**
             * 	EchoClient客户端开启两个线程分别完成不同的工作：
             * 	读取线程ReadThread用于从标准输入当中读取字符串，并且把字符串写到服务器端
             * 	写入线程WriteThread用于从服务器端接受Echo字符串
             * *//*

            EchoClient echoClient = new EchoClient();
            ReadThread readThread = echoClient.new ReadThread();
            WriteThread writThread = echoClient.new WriteThread();
            //开启线程
            writThread.start();
            readThread.start();
            //等待线程结束
            writThread.join();
            readThread.join();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //读取线程ReadThread用于从标准输入当中读取字符串，并且把字符串写到服务器端
    private class ReadThread extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            BufferedInputStream bis = null;
            byte[] buf = new byte[1024];
            int readByte = -1;
            String content = null;
            try {
                //通过客户端Socket获取和服务端通信的InputStream
                bis = new BufferedInputStream(connection.getInputStream());
                //开始循环往服务器端写入需要处理的Echo数据
                while (true) {
                    */
/**
                     * 从服务器的读取流中读取数据，
                     * 如果服务器没有返回往流中写入数据，则客户端无法读取数据，会一直阻塞等待
                     *//*

                    readByte = bis.read(buf);

                    content = new String(buf, 0, readByte);
                    System.out.println("<=== Server Echo : " + content);
                    */
/**
                     * 客户端如果读到返回的字符串为over就结束客户端
                     *//*

                    if (content.equalsIgnoreCase(END_FLAG)) {
                        System.out.println(">>> Echo End ! <<<");
                        break;
                    }
                }
            } catch (IOException e) {
                */
/**
                 * 当服务器接受到结束标志符时，END_FLAG标识客户端需要关闭服务，服务端的写入流将会关闭，
                 * 而以上while循环中客户端的读取流还在读取数据时就会抛出异常，此时说明服务结束
                 *//*

                //System.out.println("Server Write Thread is closed, the client Read Thread closed too");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //写入线程WriteThread用于从服务器端接受Echo字符串
    private class WriteThread extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            BufferedReader br = null;
            BufferedOutputStream bos = null;
            String content = null;
            byte[] buf = null;
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                bos = new BufferedOutputStream(connection.getOutputStream());
                //从标准输入中获取字符串，再写到服务器的流中
                while (true) {
                    System.out.println("===> Please input :");
                    //从标准输入中读取，会阻塞
                    content = br.readLine();
                    buf = content.getBytes();
                    //往服务器读取流中写入数据
                    bos.write(buf, 0, buf.length);
                    bos.flush();
                    //判断是否为结束标识符，结束写入线程
                    if (content.equalsIgnoreCase(END_FLAG)) {
                        //System.out.println("---> Client echo end!");
                        break;
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
}

*/
