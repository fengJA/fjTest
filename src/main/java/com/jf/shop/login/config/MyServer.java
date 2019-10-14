package com.jf.shop.login.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author fengj
 * @date 2019/8/8 -8:03
 */
public class MyServer {
    public static int DEFAULT_PORT = 8080;
    public static boolean flages = true;
    public static boolean userInfo = true;
    public static String otherClientName;
    public static HashMap<String,SocketChannel> serverSocketChannelHashMap;
    public static Map<Integer,String> allClient;


    public static void main(String[] args) {

        int port = DEFAULT_PORT;
       /* try {
            port = Integer.parseInt(args[0]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }*/
        System.out.println("Listening for connections on port " + port);

        ServerSocketChannel serverChannel = null;
        Selector selector;
        serverSocketChannelHashMap = new HashMap<>();
        allClient = new HashMap<>();
        try {
            serverChannel = ServerSocketChannel.open();
//            int localPort = serverChannel.socket().getLocalPort();
//            System.out.println("localPort is :" + localPort);
            ServerSocket ss = serverChannel.socket();

            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from "+ client);

                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);

                        System.out.println("buffer是："+ buffer.toString().length());
                    }

                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        int read = client.read(output);
                        if (read == -1){
                            client.close();
                        }else {
                            System.out.println("收到的"+ new String( output.array()));
                            for(SelectionKey key1 : selector.keys())
                            {
                                Channel targetchannel = key1.channel();
                                if(targetchannel instanceof SocketChannel )
                                {
                                    SocketChannel dest = (SocketChannel)targetchannel;
                                    output.flip();
                                    dest.write(output);
                                }
                            }
                        }

                    }

                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        client.write(output);
                        output.compact();
                    }

                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }
}
