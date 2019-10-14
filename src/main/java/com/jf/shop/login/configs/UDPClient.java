package com.jf.shop.login.configs;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            //在端口0打开一个Socket，通过指定端口0，让java随机分配一个可用端口
            datagramSocket = new DatagramSocket(0);
            //设置超时时间很重要，因为TCP中导致IOException的问题在UDP中会悄无声息的消失
            datagramSocket.setSoTimeout(10000);
            //指定要连接的远程主机和远程端口,host是远程主机地址
            InetAddress host = InetAddress.getLocalHost();
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, 9090);
            //法二：
            SocketAddress host1 = new InetSocketAddress("127.0.0.1",8888);
            DatagramPacket request1 = new DatagramPacket(new byte[1], 1, host1);
            //接收服务器响应
            byte[] date = new byte[1024];
            DatagramPacket response = new DatagramPacket(date, date.length);
            datagramSocket.send(request);
            datagramSocket.receive(response);
            //得到响应的信息
            //response.getData()返回一个byte数组，其中包含数据报中的数据,第一种将其转换为String类型
            String responseMsg = new String(response.getData(), 0, response.getLength(), "utf-8");
            //法二：如果数据报中不包含文本，就用ByteArrayInputStream，注意必须用getLength()和getOffset()，getData()中的数据也能会混入其他数据
            //getLength()：返回返回数据报中数据的字节数，不一定等于response.getData().length()的长度
            //getOffset():对于getData()返回的数组，该方法会返回数组中的一个位置，即开始填充数据报数据的那个位置
            InputStream responseMsg1 = new ByteArrayInputStream(response.getData(), response.getOffset(), response.getLength());
            DataInputStream dataInputStream = new DataInputStream(responseMsg1);
            //可以用DataInputStream的readChar()、readLong()等方法读取数据
            char c = dataInputStream.readChar();
            System.arraycopy(response.getData(), 0, date, request1.getOffset(), request1.getLength());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (datagramSocket != null){
                datagramSocket.close();
            }
        }
    }
}
