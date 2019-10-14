package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSender {
    public static void main(String[] args) {
        InetAddress address = null;
        int port = 0;
        //ttl为1，确保数据不会传到本地子网外
        byte ttl = (byte) 1;
        try {
            address = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
            if (args.length > 2){
                ttl = (byte) Integer.parseInt(args[2]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("this port already used");
            System.exit(1);
        }

        byte[] bytes = "I am Message".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
        try(MulticastSocket socket = new MulticastSocket();) {

            socket.setTimeToLive(ttl);
            socket.joinGroup(address);
            for (int i = 0; i < 10; i++) {
                socket.send(packet);
            }
            socket.leaveGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
