package com.jf.shop.login.chart;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private JTextArea serverMsg;
    private JButton send;
    private JPanel ser;
    private HashMap<String, Socket> allClient = new HashMap<>();
    Selector selector = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        frame.setContentPane(new Server().ser);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public Server() {
        send.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void serverSocket(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        Selector selector = null;
        ServerSocketChannel serverSocketChannel;
        try {
           /* ServerSocket serverSocket = new ServerSocket(6667);
            while (true){
                Socket accept = serverSocket.accept();

            }*/
           serverSocketChannel = ServerSocketChannel.open();
           serverSocketChannel.configureBlocking(false);//只有非阻塞状态才可以用Selector

           ServerSocket serverSocket = serverSocketChannel.socket();
           serverSocket.bind(new InetSocketAddress(6667));
           selector = Selector.open();
           serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                selector.select();//等待某信号就绪
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();// selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
            Iterator<SelectionKey> iterator = selectionKeys.iterator();// Selected-key Iterator 代表了所有通过 select() 方法监测到可以进行 IO 操作的 channel

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                iterator.remove();

                // 客户端请求连接事件，接受客户端连接就绪
                try {
                    if (key.isAcceptable()) {
                        handleAccept(key);

                    } else if (key.isReadable()) {// 监听到读事件，对读事件进行处理
                        handleRead(key);
                    }else if (key.isWritable()){
                        handleWriter(key);
                    }

                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        // 获取客户端连接通道
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = server.accept();
        socketChannel.configureBlocking(false);

        // 信息通过通道发送给客户端
//        socketChannel.write(ByteBuffer.wrap(new String("Hello Client!").getBytes()));

        // 给通道设置读事件，客户端监听到读事件后，进行读取操作
        SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        clientKey.attach(buffer);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer outPut = (ByteBuffer) key.attachment();//得到缓冲区的数据
        outPut.clear();//清空缓存区的内容
        int read = channel.read(outPut);
        if (read == -1){
            channel.close();//没有收到信息，则关闭
        }/*else {
            String recMsg = Charset.forName("utf-8").newDecoder().decode(outPut).toString();
        }*/

        // 从通道读取数据到缓冲区
       /* ByteBuffer buffer = ByteBuffer.allocate(128);
        channel.read(buffer);

        // 输出客户端发送过来的消息
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("server receive msg from client：" + msg);*/
    }

    private void handleWriter(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer outPut = (ByteBuffer) key.attachment();
        outPut.flip();// 将缓冲区准备为数据传出状态
        channel.write(outPut);
        outPut.compact();

    }


    public void receiveMsg(String msg){
         serverMsg.append(msg + "\r\n");
    }

    public void sendMsgToClient(Socket clientSocket, String msg){
        Set<String> getClient = allClient.keySet();
        Iterator<String> iterator = getClient.iterator();
        while (iterator.hasNext()){
            String client = iterator.next();
            Socket cliSocket = allClient.get(client);
            if (cliSocket != clientSocket){
                try {
                    Writer out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
                    out.write(msg);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
