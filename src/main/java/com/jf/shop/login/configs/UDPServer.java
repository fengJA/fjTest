package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {
    private static final Logger adius = Logger.getLogger("request");
    private static final Logger errors = Logger.getLogger("errors");
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket();){
            while (true){
                try {
                    //要在调用receive()方法之前调用，如果没有设置，就默认永不超时
                    server.setSoTimeout(10000);
                    server.setReceiveBufferSize(1024);
                    DatagramPacket rece = new DatagramPacket(new byte[1024], 1024);
                    server.receive(rece);

                    String respmsg = new Date().toString();
                    //将字符窜转换为byte数组
                    byte[] data = respmsg.getBytes("utf-8");

                    //  1、rece是从网络上接收数据包，则rece.getAddress()返回的地址是发送该数据包的主机地址，
                    // 相反，如果该数据包是本地的，那个返回的就是要发往的那个主机地址，
                    //  2、rece.getPort()类似
                    //  3、rece.getSocketAddress()返回一个SocketAddress对象，其中包含远程主机的IP和端口，返回情况类似getAddress()
                    //如果使用非阻塞IO，DatagramChannel可以接收SocketAddress，但是不会就收单独的InetAddress对象和端口
                    DatagramPacket resp = new DatagramPacket(data,data.length,rece.getAddress(),rece.getPort());

                    server.send(resp);
                    adius.info(respmsg +":"+ rece.getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                    errors.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
