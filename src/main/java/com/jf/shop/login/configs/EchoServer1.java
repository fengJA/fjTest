package com.jf.shop.login.configs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EchoServer1 {

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

                        /*if (userInfo){
                            InputStream inputStream = client.socket().getInputStream();
                            BufferedReader otherName = new BufferedReader(new InputStreamReader(inputStream));
                            String s = otherName.readLine();
                            allClient.put(client.socket().getPort(), s);
                            System.out.println("port is:" + client.socket().getPort());
                            userInfo = false;
                        }*/
                        if (client.socket().isConnected()){
//                        if (userInfo){
                            InputStream inputStream = client.socket().getInputStream();
                            BufferedReader otherName = new BufferedReader(new InputStreamReader(inputStream));
                            String s = otherName.readLine();
                            System.out.println("s是:"+s);
//                            allClient.put(client.socket().getPort(), s);
                            serverSocketChannelHashMap.put(s, client);

                            System.out.println("serverSocketChannelHashMap :" + serverSocketChannelHashMap);

                            flages = true;
                        }

                        if (flages){
                            InputStream inputStream = client.socket().getInputStream();
                            BufferedReader otherName = new BufferedReader(new InputStreamReader(inputStream));
                            otherClientName = otherName.readLine();
//                            serverSocketChannelHashMap.put(otherClientName, client);
                            allClient.put(client.socket().getPort(), otherClientName);
                            flages = false;
                            System.out.println("allClient:"+allClient);
                            System.out.println("port is:" + client.socket().getPort());
                            System.out.println("Accepted connection from "+ client);
                            System.out.println("我是OtherClientName："+otherClientName );
                        }

//                        System.out.println("Accepted connection from " + "名字"+ s +";"+ client);
//                        System.out.println("Accepted connection from "+ client);
                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);

                        System.out.println("buffer是："+ buffer.toString().length());
                    }

                    /*Set<String> otherClientKey = serverSocketChannelHashMap.keySet();
                    ServerSocketChannel serverSocketChannel = serverSocketChannelHashMap.get(otherClientKey);
                    if(true){
                        serverSocketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                        Set<SelectionKey> keys = selector.selectedKeys();
                    }*/

                    for (String map : serverSocketChannelHashMap.keySet()){
//                        System.out.println("开心："+serverSocketChannelHashMap.get(map));
//                        allClient.remove(map);
                        SocketChannel clientChannel = serverSocketChannelHashMap.get(map);

//                        System.out.println("clientName:" +clientName);
//                        System.out.println("otherClientName:"+otherClientName);
                        if (clientChannel.finishConnect()){
                            for (Integer ourClientPort : allClient.keySet()){
                                String othersClientName = allClient.get(ourClientPort);
                                /*System.out.println("map:"+map);//zs
                                System.out.println("otherClientName:"+othersClientName);//ls*/
                                if (map.equals(othersClientName) && clientChannel.socket().getPort() != ourClientPort){
                                   /* System.out.println("clientChannel.socket().getPort():"+clientChannel.socket().getPort());//53578
                                    System.out.println("otherClientPort:"+otherClientPort);//53572*/


                                    SocketChannel socketChannel = serverSocketChannelHashMap.get(allClient.get(clientChannel.socket().getPort()));
                                    if (socketChannel.finishConnect()){

                                        if (key.isReadable()) {
                                            ByteBuffer output = (ByteBuffer) key.attachment();
                                            System.out.println("收到的"+ new String( output.array()));
                                            int read = socketChannel.read(output);
                                            if(read <= 0){
                                                System.out.println("read:" + read);
//                                                socketChannel.close();
                                                continue;
                                            }else {
                                                output.flip();
                                                socketChannel.write(output);
                                            }

                                        /*SocketChannel client = (SocketChannel) key.channel();
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
                                        }*/

                                        }


                                        if (key.isWritable()) {
//                                        SocketChannel client = (SocketChannel) key.channel();
                                            ByteBuffer output = (ByteBuffer) key.attachment();
                                            output.flip();
                                            socketChannel.write(output);
                                            output.compact();
                                        }
                                    }
                                }
                            }
                        }

//                        allClient.remove(map);
                        /*Set<Integer> clientPortKey = allClient.keySet();
                        Iterator<Integer> iterator1 = clientPortKey.iterator();
                        while (iterator1.hasNext()){
                            Integer next = iterator1.next();
                            iterator1.remove();

                        }*/
                    }

                    /*Set<Integer> clientPortKey = allClient.keySet();
                    Iterator<Integer> iterator1 = clientPortKey.iterator();
                    while (iterator1.hasNext()){
                        Integer next = iterator1.next();
                        iterator1.remove();
                        String clientName = allClient.get(next);
                        System.out.println("clientName:" +clientName);
                        System.out.println("otherClientName:"+otherClientName);
                        if (!clientName.isEmpty()){
                            if (clientName.equals(otherClientName)){
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
                      *//* if (output.hasRemaining()){
                           output.flip();
                           client.write(output);
                           output.compact();
                       }else {
                           client.close();
                       }*//*
                                    output.flip();
                                    client.write(output);
                                    output.compact();
                                }
                            }
                        }
                    }*/

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