package com.jf.shop.login.socketTest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolDayTimeTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(9999);){
            while (true){
                try ( Socket accept = serverSocket.accept();){
                    Callable<Void> callBackThread = new CallBackThread(accept);
                    pool.submit(callBackThread);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CallBackThread implements Callable<Void> {

        private Socket socket;

        public CallBackThread(Socket socket) {
            this.socket = socket;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Void call() {
            Writer writer = null;
            try {
                writer = new OutputStreamWriter(socket.getOutputStream());
                Date date = new Date();
                writer.write(date.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           return null;
        }
    }
}
