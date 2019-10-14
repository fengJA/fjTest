package com.jf.shop.login.configs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

//是一个父类
public abstract class UDPServerThread implements Runnable{
    private final int port;
    private final int bufferSize;//默认大小
    private final Logger logger = Logger.getLogger(UDPServerThread.class.getCanonicalName());
    private volatile boolean isShutDown = false;

    public UDPServerThread(int port, int bufferSize) {
        this.port = port;
        this.bufferSize = bufferSize;
    }

    public UDPServerThread(int port) {
        this(port,8192);
    }

    @Override
    public void run() {
        byte[] bytes = new byte[bufferSize];
        try (DatagramSocket server = new DatagramSocket(port)){
            server.setSoTimeout(10000);//每10秒检查一次是否关闭
            while (true){
                if (isShutDown){
                    return;
                }
                DatagramPacket request = new DatagramPacket(bytes, bytes.length);
                try {
                    server.receive(request);
                    this.respond(server, request);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.log(Level.WARNING, e.getMessage(), e);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "can not bind this port", e);
        }
    }
    public abstract void respond(DatagramSocket socket,DatagramPacket request) throws IOException;

    public void shutDown(){
        this.isShutDown = true;
    }


}
