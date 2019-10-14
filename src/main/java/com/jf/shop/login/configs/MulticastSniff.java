package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSniff {
    public static void main(String[] args) {
        InetAddress group = null;
        int port = 0;
        //从 命令行读取数据
        try {
            group = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
        } catch (UnknownHostException e) {
            System.out.println("this port already used");
            System.exit(1);
        }

        MulticastSocket socket =  null;
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(group);
            byte[] bytes = new byte[1024];
            while (true){
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                System.out.println(new String(packet.getData(),"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null){
                try {
                    socket.close();
                    socket.leaveGroup(group);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
