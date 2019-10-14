package com.jf.shop.login.socketTest;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestProcesserThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(RequestProcesserThread.class.getCanonicalName());
    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket socket;

    public RequestProcesserThread(File rootDirectory, String indexFileName, Socket socket) {
        if (rootDirectory.isFile()){
            throw new IllegalArgumentException("it is not a directory");
        }
        try {
            rootDirectory.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.rootDirectory = rootDirectory;
        if (indexFileName != null){
            this.indexFileName = indexFileName;
        }
        this.socket = socket;
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
        String root = rootDirectory.getPath();
        try {
            OutputStream out = new BufferedOutputStream(socket.getOutputStream());
            Writer writer = new OutputStreamWriter(out);
            Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"ASCII"));
            StringBuilder sb = new StringBuilder();
            while (true){
                int c = in.read();
                if (c == '\r' || c == '\n'){
                    break;
                }
                sb.append((char) c);
            }
            String s = sb.toString();
            LOGGER.info(socket.getRemoteSocketAddress() + " " + s);//getRemoteSocketAddress()返回的是地址和端口号

            String[] token = s.split("\\s+");
            String method = token[0];
            String version = "";
            if (method.equals("GET")){//如果是GET方法
                String fileName = token[1];
                if (fileName.endsWith("\\")){
                    fileName += indexFileName;
                }
                if (token.length > 2){
                    version = token[2];
                }
                String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(fileName);
                File theFile = new File(rootDirectory + fileName.substring(1, fileName.length()));

                if (theFile.canRead() && theFile.getCanonicalPath().startsWith(root)){//判断该路径是绝对路径，不会存在".."情况将目录全部遍历，getCanonicalPath()返回的就是解析后的路径
                    byte[] theDate = Files.readAllBytes(theFile.toPath());
                    if (version.startsWith("HTTP/")){
                        sendHeader(writer, "HTTP/1.0 200 OK", contentTypeFor, theDate.length);

                        out.write(theDate);//发送的文件可能是图片或者其他二进制数据，所以要用底层的输出流，而不是使用Writer
                        out.flush();
                    }
                }else {//无法找到文件
                    String notFoundFile = new StringBuilder("<HTML>\r\n")
                            .append("找不到文件\r\n")
                            .append("</HTML>\r\n").toString();
                    if (version.startsWith("HTTP/")){
                        sendHeader(writer, "HTTP/1.0 404 NOT FOUND","text/html;chartset=utf-8",notFoundFile.length());
                    }
                    writer.write(notFoundFile);
                    writer.flush();
                }
            }else {//如果不是Get方法
                String notFoundFile = new StringBuilder("<HTML>\r\n")
                        .append("该方法不是Get方法\r\n")
                        .append("</HTML>\r\n").toString();
                if (version.startsWith("HTTP/")){
                    sendHeader(writer, "HTTP/1.0 501 NOT FOUND","text/html;chartset=utf-8",notFoundFile.length());
                }
                writer.write(notFoundFile);
                writer.flush();
            }

        } catch (IOException e) {
            LOGGER.log(Level.WARNING , "error logging to " +socket.getRemoteSocketAddress() ,e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendHeader(Writer out, String responseCode, String contentType, int contentLength){
        try {
            out.write(responseCode + "\r\n");
            Date date = new Date();
            out.write(date + "\r\n");
            out.write("Server :JHTTP 2.0" );
            out.write("Content-Length:" + contentType);
            out.write("Content-Type:" + contentType);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

































