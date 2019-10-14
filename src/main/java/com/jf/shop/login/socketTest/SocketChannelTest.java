package com.jf.shop.login.socketTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class SocketChannelTest {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("10.9.5.24", 8888));
            socketChannel.configureBlocking(false);//设置为非阻塞模式,只有在无参构造中才可以使用，上面已经绑定了，设置也是来不及了
            ByteBuffer buffer = ByteBuffer.allocate(74);//创建一个容量为74的ByteBuffer
            //利用通道可以直接可以通道本身，
            // 必须用ByteBuffer，将ByteBuffer对象传递给read()方法，通道会用从Socket读到的数据填充这个缓冲区
            int read = socketChannel.read(buffer);

            // 传统的做法是获取ByteBuffer中的字节数组，然后封装到OutPutStream中
            //如果缓存中有数据，就会被复制到System.out，Channels.newChannel可以将缓存的数据写入通道，
            WritableByteChannel outPut = Channels.newChannel(System.out);

            while (read != -1){
                //然后将读取的数据写入到与System.out相连的通道，
                // 在此之前，必须先回绕【回绕可以保持缓冲区的数据不变，只是准备写入数据】（flip）缓冲区，使输出通道缩写的数据从所读取的数据开头而不是结尾开始输出（即写，不会倒序）
                buffer.flip();
                outPut.write(buffer);

                buffer.clear();//不要每次都创建新的缓冲区，拉低性能，直接清空，与回绕不同，老数据还没有被覆盖，但是很快就会被覆盖
            }

            //处于非阻塞模式下，read可能为0，则用下面的方式判断
            while (true){
                if (read > 0){
                    buffer.flip();
                    outPut.write(buffer);
                    buffer.clear();
                }else if (read == -1){
                    //一般不会发生，除非服务器出错
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
