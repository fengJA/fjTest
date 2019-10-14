package com.jf.shop.login.socketTest;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

@Slf4j
public class EchoServer {
    private static final Logger errLogger = Logger.getLogger("err" + EchoServer.class.getCanonicalName());

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel;
        Selector selector = null;
        try {
            //创建通道
            serverSocketChannel = ServerSocketChannel.open();
//            InetSocketAddress inetSocketAddress = new InetSocketAddress(9999);
            //绑定到指定的端口
            ServerSocket socket = serverSocketChannel.socket();
            socket.bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);//设置为非阻塞

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//将通道(serverSocketChannel)注册到通道管理器(Selector)
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true){
            try {
                selector.select();// 当注册事件到达时，方法返回，否则该方法会一直阻塞
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            // 获取监听事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //迭代处理
            while (iterator.hasNext()){//迭代处理
                SelectionKey next = iterator.next();//获取监听事件
                iterator.remove();//移除事件，避免重复处理

                // 客户端请求连接事件，接受客户端连接就绪
                try {
                    if (next.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel) next.channel();// 获取客户端连接通道
                        SocketChannel client = channel.accept();
                        System.out.println("Accept connection from" + client);
                        client.configureBlocking(false);
                        SelectionKey clientRegister = client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);//表示客户端可以从服务器读，可以写
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientRegister.attach(buffer);

                    }else if (next.isReadable()){//监听到读事件，读取客户端发送过来的消息
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer attachment = (ByteBuffer) next.attachment();
                        channel.read(attachment);

                        byte[] bytes = attachment.array();
                        String s = new String(bytes);
                        System.out.println("Client Message:" + s);

                    }else if(next.isWritable()){
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer output = (ByteBuffer) next.attachment();
                        output.flip();
                        channel.write(output);
                        output.compact();
                    }
                } catch (IOException e) {
                    next.cancel();
                    try {
                        next.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }

        }

    }
}
