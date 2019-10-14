package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest {
    private static final int MAX_BUFFER_SISE = 65507;
    public static void main(String[] args) {
        try {
            //法一：java7以后使用
            //创建DatagramChannel对象
            DatagramChannel channel = DatagramChannel.open();
            SocketAddress address = new InetSocketAddress(8888);
            //将通道绑定到8888端口
            channel.bind(address);

            //法二：需要用到DatagramSocket
            DatagramChannel channel1 = DatagramChannel.open();
            DatagramSocket socket = channel1.socket();
            SocketAddress address1 = new InetSocketAddress(8889);
            socket.bind(address1);

            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_BUFFER_SISE);
            while (true){
                //接收
                channel1.receive(buffer);
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.println(buffer.get());
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
