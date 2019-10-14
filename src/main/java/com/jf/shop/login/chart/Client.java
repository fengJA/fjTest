package com.jf.shop.login.chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private JTextArea clientMsg;
    private JButton send;
    private JTextField reName;
    private JButton submit;
    private JPanel cli;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().cli);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public Client() {
        submit.addActionListener(new ActionListener() {
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

    public void clientSocket(){
        try {
//            Socket socket = new Socket("10.9.5.24",6667);
            SocketChannel clientSocket = SocketChannel.open(new InetSocketAddress("10.9.5.24", 6667));
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
//            buf.put(newData.getBytes());

            buf.flip();

            while (buf.hasRemaining()) {
                clientSocket.write(buf);
            }

//        socketChannel.configureBlocking(false);
//        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
//
//        while(! socketChannel.finishConnect() ){
//            //wait, or do something else...
//        }

            clientSocket.close();

//            while (true)

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
