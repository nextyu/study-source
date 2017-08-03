package com.nextyu.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created on 2017-08-03 15:02
 *
 * @author nextyu
 */
public class Processor {
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void process(final SelectionKey selectionKey) {
        SERVICE.submit(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            int count = socketChannel.read(buffer);
            if (count < 0) {
                socketChannel.close();
                selectionKey.cancel();

                System.out.println(socketChannel + "\t Read ended");

                return null;
            } else if (count == 0) {
                return null;
            }

            System.out.println(socketChannel + "\t Read message " + new String(buffer.array()));
            return null;


        });
    }
}
