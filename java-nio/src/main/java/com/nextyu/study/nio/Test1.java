package com.nextyu.study.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;

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
    public void selector() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(10000));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }

        }

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
    public void writingToSocketChannel() throws Exception {
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

    @Test
    public void pipe() throws Exception {
        // Writing to a Pipe
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

        String data = "qqq";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        // Reading from a Pipe
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buffer1 = ByteBuffer.allocate(48);
        int read = sourceChannel.read(buffer1);


    }

    @Test
    public void creatingAbsolutePath() {
        Path path = Paths.get("c:\\data\\myfile.txt");
    }

    @Test
    public void creatingRelativePath() {
        // d:\data\projects
        Path projects = Paths.get("d:\\data", "projects");
        // d:\data\projects\a-project\myfile.txt
        Path file = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
    }

    @Test
    public void file() throws Exception {
        // Files.exists()
        Path path = Paths.get("data/logging.properties");




        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        // Files.createDirectory()
        Path newDir = Files.createDirectory(path);

        // Files.copy()
        Path sourcePath = Paths.get("data/logging.properties");
        Path destinationPath = Paths.get("data/logging-copy.properties");
        Files.copy(sourcePath, destinationPath);

        // Overwriting Existing Files
        Files.copy(sourcePath, destinationPath,
                StandardCopyOption.REPLACE_EXISTING);

        // Files.move()
        Files.move(sourcePath, destinationPath,
                StandardCopyOption.REPLACE_EXISTING);

        // Files.delete()
        Files.delete(path);
    }

    @Test
    public void walkFileTree() throws Exception {
        Path path = Paths.get("F:\\IdeaProjects_gitoschina\\summer-mall-api");
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre visit dir:" + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visit file failed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post visit directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });


    }

    @Test
    public void searchingForFiles() throws Exception {

        Path rootPath = Paths.get("data");
        String fileToFind = File.separator + "README.txt";

        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileString = file.toAbsolutePath().toString();
                //System.out.println("pathString = " + fileString);

                if (fileString.endsWith(fileToFind)) {
                    System.out.println("file found at path: " + file.toAbsolutePath());
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void deletingDirectoriesRecursively() throws Exception {
        Path rootPath = Paths.get("data/to-delete");

        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("delete file: " + file.toString());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readingDataViaFuture() throws Exception {
        Path path = Paths.get("F:/test.txt");
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        Future<Integer> operation = fileChannel.read(buffer, position);

        while (!operation.isDone()) ;

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    @Test
    public void readingDataViaCompletionHandler() throws Exception {
        Path path = Paths.get("F:/test.txt");
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }

    @Test
    public void WritingDataViaFuture() throws Exception {
        Path path = Paths.get("data/test-write.txt");

        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("test data".getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();

        while (!operation.isDone()) ;

        System.out.println("Write done");
    }

    @Test
    public void writingDataViaCompletionHandler() throws Exception {
        Path path = Paths.get("data/test-write.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("test data".getBytes());
        buffer.flip();

        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Write failed");
                exc.printStackTrace();
            }
        });
    }

}
