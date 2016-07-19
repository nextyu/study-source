package com.nextyu.study.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * created on 2016-07-18 14:46
 *
 * @author nextyu
 */
public class Test1 {

    @Test
    public void testBasicBufferUsage() throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:/test.txt", "rw");
        FileChannel channel = file.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buffer = ByteBuffer.allocate(48);

        // read into buffer
        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();// make buffer ready for read

            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get()); // read 1 byte at a time
            }

            buffer.clear(); // make buffer ready for writing
            read = channel.read(buffer);
        }

        file.close();

    }

    @Test
    public void testFileChannel() {

    }

    @Test
    public void testPath() {
        Path path = Paths.get("F:/");
    }
}
