package com.nextyu.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * created on 2017-08-03 9:04
 *
 * @author nextyu
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(10000));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null != socketChannel) {
                ByteBuffer buffer = ByteBuffer.allocate(48);
                buffer.clear();
                int read = socketChannel.read(buffer);
                while (read > 0) {
                    buffer.flip();
                    System.out.println(new String(buffer.array()));


                    buffer.clear();
                    read = socketChannel.read(buffer);
                }
                socketChannel.close();
            }
        }
    }
}
