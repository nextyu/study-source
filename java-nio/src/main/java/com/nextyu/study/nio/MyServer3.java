package com.nextyu.study.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * created on 2017-08-03 14:39
 *
 * @author nextyu
 */
public class MyServer3 {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(10000));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("Accept request from " + socketChannel.getRemoteAddress());
                    SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    readKey.attach(new Processor());
                } else if (key.isReadable()) {
                    Processor processor = (Processor) key.attachment();
                    processor.process(key);
                }

                selectionKeys.remove(key);
            }
        }


    }
}
