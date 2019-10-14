package com.jf.shop.login.socketTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelTest {
    public static void main(String[] args) {

        //用该数组中的内容填充缓冲区
        byte [] rotation = new byte[95*2];
        for (byte i = ' '; i < '~';i++){
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }

        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);//需要在accept()之前调用
            ServerSocket socket = serverChannel.socket();
            socket.bind(new InetSocketAddress(8888));//绑定到端口,则服务器在端口8888监听入栈连接

            //接受连接，非阻塞的accept()几乎会立即返回NULL，要确保对此进行检查，否则当试图使用这个socket（实际为null），会抛异常
            SocketChannel clientChannel = serverChannel.accept();
            clientChannel.configureBlocking(false);//非阻塞模式，可以并发

            //存在两个通道：服务器通道和客户端通道（会无限运行下去），此外：服务器通道会创建更多的打开的客户端通道，传统方法中，为每一个连接分配一个线程，代价大
            // Selector会迭代处理所有准备好的连接，open()创建选择器
            Selector selector = Selector.open();

            //使用每个通道的register()方法向监视他的选择器进行注册，对于服务器通道，只需要判断OP_ACCEPT 是否准备好接受新连接
            serverChannel.register(selector, SelectionKey.OP_ACCEPT );

            //对于客户端通道，只需要判断OP_READ或者OP_WRITE是否可以读或者写
            clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            while (true){
                selector.select();//检查是否有可操作的数据，如果是长时间的连接，放在while (true)里面

                //选择器找到了一个就绪通道，selectedKeys()方法会返回一个Set，其中对应各个就绪通道分别包含一个SelectionKey对象，
                // 否则返回一个空集，这两种情况都可以用Iterator处理
                Set<SelectionKey> redayKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = redayKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();//去掉，以免重复处理

                    try {
                        if (key.isAcceptable()){
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel conn = server.accept();
                            conn.configureBlocking(false);
    //                        SelectionKey clientRegister = conn.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                            //每个SelectionKey都有一个任意的Object类型的附件，他通常用于保存一个指示当前连接状态的对象。
                            // 通常，可以将通道要写入网络的缓冲区存储在这个对象。一旦缓冲区完全排空（drain），将重新填满
                            SelectionKey clientRegister = conn.register(selector, SelectionKey.OP_WRITE );
                            ByteBuffer buffer = ByteBuffer.allocate(111);
                            clientRegister.attach(buffer);
                        }else if (key.isWritable()){
                            //向通道写入数据
                            SocketChannel client = (SocketChannel) key.channel();
                            //首先获取键的附件，将他转换为ByteBuffer
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            //hasRemaining()检查缓冲区中是否还剩余未写的就数据，如果有就写入到通道。
                            // 否则，用rotation数组中的下一行数据重新填充缓冲区，并写入到通道
                            if(!buffer.hasRemaining()){
                                //用下一行数据重新填充缓冲区，确定最后一行从哪里开始
                                buffer.rewind();
                                //从缓存区中读取第一个数据字节,即得到上一次的首字符，每个get()调用都会将位置前移一个，直到到达limit（限度）位置，hasRemaining()返回false
                                int first = buffer.get();
                                //递增到下一个字符，即准备改变缓冲区的数据
                                buffer.rewind();
                                //该数据字节要减去空格字符（32），因为rotation数组中的第一个字符是空格，
                                // 则可知缓冲区当前从哪个索引开始，要加1得到下一行的开始索引，并重新填充缓冲区
                                int position = first - ' ' + 1;
                                //将数据从rotation中复制到缓冲区
                                buffer.put(rotation,position,72);
                                //准备缓冲区进行写入,flip()就是将限度设置为当前位置，起始位置设为0，将0到限度范围内的数据写出
                                buffer.flip();
                            }
                            client.write(buffer);
                        }
                    } catch (IOException e) {
                        //客户端中断后会抛出异常，然后取消这个键，关闭
                        key.cancel();
                        key.channel().close();
                    }
                }
            }


        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
