package com.jf.shop.login.socketTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingHttpServerTest {
    private int port;
    private ByteBuffer contentBuffer;
    public NonBlockingHttpServerTest(int port,ByteBuffer date,String MEMIType,String encoding) {
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n"+
                "Content-Length:"+date.limit()+"\r\n"+
                "Content-Type" + MEMIType + "\r\n";
        byte[] bytes = header.getBytes(Charset.forName(encoding));
        ByteBuffer buffer = ByteBuffer.allocate(date.limit()+header.length());
        buffer.put(bytes);
        buffer.put(date);
        buffer.flip();
        this.contentBuffer = buffer;
    }
    public void run(){
        Selector selector = null;
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            ServerSocket ss = server.socket();
            ss.bind(new InetSocketAddress(port));
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT );
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            if (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()){
                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                        SocketChannel client = serverChannel.accept();
                        System.out.println("Accept connection " + client);
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }else if (key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                       /* client.write(output);
                        output.compact();//压缩*/
                        if (output.hasRemaining()){
                            client.write(output);
                        }else {
                            client.close();
                        }
                    }else if (key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        client.read(output);
                        //将通道切换为只写模式
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());
                    }
                } catch (IOException e) {
                   key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("have not file ");
            return;
        }
        try {
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            Path path = FileSystems.getDefault().getPath(args[0]);
            byte[] bytes = Files.readAllBytes(path);
            ByteBuffer date = ByteBuffer.wrap(bytes);
            int port = 80;
            String encoding = "UTF-8";
            NonBlockingHttpServerTest nos = new NonBlockingHttpServerTest(port, date, contentType, encoding);
            nos.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
