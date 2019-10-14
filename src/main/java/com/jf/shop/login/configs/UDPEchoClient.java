package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPEchoClient {
    private final static int LIMT = 100;
    public static void main(String[] args) {
        SocketAddress socket;
        try {
            socket = new InetSocketAddress("127.0.0.1", 8888);
        } catch (RuntimeException e) {
            System.out.println("can not connect");
            return;
        }

        try (DatagramChannel channel = DatagramChannel.open()){
            channel.configureBlocking(false);
            channel.connect(socket);
            channel.setOption(StandardSocketOptions.SO_BROADCAST, true);//开启广播

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int n = 0;
            int numSize = 0;

            while (true){
                if (numSize == LIMT){
                    break;
                }
                selector.select(10000);
                Set<SelectionKey> keys = selector.selectedKeys();

                if (keys.isEmpty() || n ==LIMT){
                    break;
                }else {
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()){
                            buffer.clear();
                            channel.read(buffer);
                            int anInt = buffer.getInt();
                            System.out.println("read :"+anInt);
                            numSize++;
                        }
                        if (key.isWritable()){
                            buffer.clear();
                            buffer.putInt(n);
                            channel.write(buffer);
                            n++;
                            if (n == LIMT){
                                //所有包都发送成功，切换为只读模式
                                key.interestOps(SelectionKey.OP_READ);
                            }
                            System.out.println("wrote:"+n);
                        }
                    }
                }
            }
            System.out.println("echo" + numSize + "out of" + LIMT +"send");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
