package com.nextyu.study.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * created on 2017-08-03 15:23
 *
 * @author nextyu
 */
public class MyServer4 {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(10000));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int coreNum = Runtime.getRuntime().availableProcessors();
        Processor2[] processor2s = new Processor2[coreNum];
        for (int i = 0; i < processor2s.length; i++) {
            processor2s[i] = new Processor2();
        }

        int index = 0;
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                keys.remove(key);

                if (key.isAcceptable()) {
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("Accept request from " + socketChannel.getRemoteAddress());
                    // round robin
                    Processor2 processor2 = processor2s[(int) ((index++) % coreNum)];

                    processor2.addChannel(socketChannel);
                    processor2.wakeup();

                }

            }

        }

    }
}
