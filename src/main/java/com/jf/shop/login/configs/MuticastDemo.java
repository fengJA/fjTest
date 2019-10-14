package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.*;

public class MuticastDemo {
    public static void main(String[] args) {
        try (MulticastSocket ms = new MulticastSocket();){
            InetAddress address = InetAddress.getByName("224.2.2.2");
            //加入组播组
            ms.joinGroup(address);
            byte[] bytes = new byte[1024];
            while (true){
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                //表示不接收自己发送的包
                ms.setLoopbackMode(true);
                ms.receive(packet);
                String s = new String(packet.getData(), "utf-8");
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moreMuticast(){
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket();
            SocketAddress address = new InetSocketAddress("224.2.2.2",40);
            NetworkInterface networkInterface = NetworkInterface.getByName("eth0");
            if (networkInterface != null){
                //允许之加入指定本地网络（eth0）的组播组（224.2.2.2）
                ms.joinGroup(address, networkInterface);
            }else {
                //如果不存在这样一个借口，就加入所有可用网络接口上的组
//                ms.joinGroup(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ms != null){
                try {
                    ms.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //发送数据
    public static void sendMsg(){
        try (MulticastSocket ms = new MulticastSocket()){
            InetAddress address = InetAddress.getByName("localhost");
            byte[] bytes = "here is some message \r\n".getBytes();
            int port = 7777;
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length,address,port);
            //设置TTL值
            ms.setTimeToLive(33);
            ms.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void choiceAddress(){
        try (MulticastSocket ms = new MulticastSocket()){
            //选择用于收发组播的地址
            InetAddress address = InetAddress.getByName("sendMulticastAddress");
            //对于MulticastSocket对象，网络接口是可变的，所以要立即设置，就不变了
            ms.setInterface(address);

            //方法二：类似InetAddress
            NetworkInterface address1 = NetworkInterface.getByName("sendMulticastAddress");
            ms.setNetworkInterface(address1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
