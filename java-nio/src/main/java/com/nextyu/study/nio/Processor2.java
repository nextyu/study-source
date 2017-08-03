package com.nextyu.study.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created on 2017-08-03 15:25
 *
 * @author nextyu
 */
public class Processor2 {
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());

    private Selector selector;

    public Processor2() throws Exception {
        selector = SelectorProvider.provider().openSelector();
        start();
    }

    private void start() {
        SERVICE.submit(() -> {
            while (true) {
                if (selector.select(500) <= 0) {
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    keys.remove(key);

                    if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        int count = socketChannel.read(buffer);
                        if (count < 0) {
                            socketChannel.close();
                            key.cancel();
                            System.out.println(socketChannel + "\t Read ended");
                        } else if (count == 0) {
                            System.out.println(socketChannel + "\t Message size is 0");
                        } else {
                            System.out.println(socketChannel + "\t Read message " + new String(buffer.array()));
                        }


                    }

                }

            }
        });
    }

    public void addChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void wakeup() {
        selector.wakeup();
    }

}
