package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServerThread {
    private final static int DEFALUT_PORT = 13;

    public UDPEchoServer() {
        super(DEFALUT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {
        DatagramPacket response = new DatagramPacket(request.getData(), request.getLength(),request.getAddress(),request.getPort());
        socket.send(response);
    }

    public static void main(String[] args) {
        UDPServerThread udpEchoServer = new UDPEchoServer();
        Thread t = new Thread(udpEchoServer);
        t.start();
    }
}
