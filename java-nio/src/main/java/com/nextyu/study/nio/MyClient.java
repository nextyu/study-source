package com.nextyu.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * created on 2017-08-03 9:15
 *
 * @author nextyu
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(10000));
        String data = "abc";

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }

        socketChannel.close();

    }
}
