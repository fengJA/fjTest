package com.jf.shop.login.chart;

import java.io.*;
import java.net.Socket;

public class ServerChartTHread implements Runnable {
    private Socket conn;
    private Server server;
    private String temp = null;
    private int msgLen;
    private String clientName;

    public ServerChartTHread(Socket conn, String clientName) {
        this.conn = conn;
        this.clientName = clientName;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            char[] chars = new char[200];
            StringBuilder sb = new StringBuilder();
            while (in.read() != -1) {
//                temp = new String(chars, 0, msgLen);
//                System.out.println("来自客户端" + conn.getPort() + "的消息:" + temp);
//                server.apppendMsg("来自客户端" + conn.getPort() + "的消息:" + temp);
//                server.sendMsgToAll(this.conn, "客户端" + conn.getPort() + "的说:" + temp);
            }

            while ((msgLen = ((Reader) in).read(chars)) != -1){
//                sb.append((char)c);
                temp = new String(chars, 0 ,msgLen);
                server.receiveMsg(clientName + ":" + temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
