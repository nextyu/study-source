package com.nextyu.study.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * created on 2016-07-18 14:46
 *
 * @author nextyu
 */
public class Test1 {

    @Test
    public void basicBufferUsage() throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:/test.txt", "rw");
        FileChannel channel = file.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buffer = ByteBuffer.allocate(2);

        // read into buffer, how many bytes were witten into the Buffer
        int read = channel.read(buffer);
        while (read != -1) {
            // switches a Buffer from writing mode to reading mode
            buffer.flip();// make buffer ready for read

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get()); // read 1 byte at a time
            }

            buffer.clear(); // make buffer ready for writing
            read = channel.read(buffer);
        }

        channel.close();
        file.close();

    }

    /**
     * the channel "scatters" the data from the channel into multiple buffers
     *
     * @throws Exception
     */
    @Test
    public void scatteringReads() throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:/test.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};
        channel.read(bufferArray);
    }

    /**
     * the channel "gathers" the data from multiple buffers into one channel.
     *
     * @throws Exception
     */
    @Test
    public void gatheringWrites() throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:/test.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

//write data into buffers

        ByteBuffer[] bufferArray = {header, body};

        channel.write(bufferArray);
    }

    @Test
    public void transferFrom() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    @Test
    public void transferTo() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }

    @Test
    public void writingDataFileChannel() throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:/test11.txt", "rw");
        FileChannel channel = file.getChannel();

        String data = "11111111111111111";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
        file.close();
    }

    @Test
    public void readingFromSocketChannel() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        socketChannel.close();
    }

    @Test
    public void writingFromSocketChannel() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }
        socketChannel.close();
    }

    @Test
    public void serverSocketChannel() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null != socketChannel) {
                // do something with socketChannel...
            }
        }
//        serverSocketChannel.close();
    }
}
