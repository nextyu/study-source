package com.nextyu.study.nio2;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;

/**
 * created on 2017-08-04 14:30
 *
 * @author nextyu
 */
public class MyTest {

    @Test
    public void watchService() throws Exception {
        Path path = Paths.get("F:\\");

        WatchService watchService = FileSystems.getDefault().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            WatchKey watchKey = watchService.take();
            List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : watchEvents) {
                System.out.println(
                        watchEvent.kind() + "--" + watchEvent.context());
            }
            watchKey.reset();
        }

    }

    @Test
    public void basicFileAttributeView() throws Exception {
        Path path = Paths.get("F:\\新建文本文档.txt");
        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
        FileTime fileTime = basicFileAttributes.creationTime();
        System.out.println(fileTime);
    }

    @Test
    public void fileStore() throws Exception {
        Path path = Paths.get("F:\\");
        FileStore fileStore = Files.getFileStore(path);
        long totalSpace = fileStore.getTotalSpace();                // 总容量
        long unallocatedSpace = fileStore.getUnallocatedSpace();    // 可使用容量
        long usedSpace = totalSpace - unallocatedSpace;             // 已使用容量

        double d = Math.pow(1024, 3);

        System.out.println(totalSpace / d);
        System.out.println(unallocatedSpace / d);
        System.out.println(usedSpace / d);

    }

    @Test
    public void fileSystems() throws Exception {
        Iterable<FileStore> fileStores = FileSystems.getDefault().getFileStores();
        for (FileStore fileStore : fileStores) {

            System.out.println(fileStore.name());
            System.out.println(fileStore.type());


            long totalSpace = fileStore.getTotalSpace();                // 总容量
            long unallocatedSpace = fileStore.getUnallocatedSpace();    // 可使用容量
            long usedSpace = totalSpace - unallocatedSpace;             // 已使用容量

            double d = Math.pow(1024, 3);

            System.out.println(totalSpace / d);
            System.out.println(unallocatedSpace / d);
            System.out.println(usedSpace / d);

            System.out.println("----------------------------------------");
        }
    }

    @Test
    public void jvmMemory() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.freeMemory();

        double d = Math.pow(1024, 3);

        System.out.println(totalMemory / d);
        System.out.println(maxMemory / d);
        System.out.println(freeMemory / d);


    }

    @Test
    public void systemMemory() throws Exception {
        OperatingSystemMXBean mxbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalPhysicalMemorySize = mxbean.getTotalPhysicalMemorySize();
        long freePhysicalMemorySize = mxbean.getFreePhysicalMemorySize();
        long usedPhysicalMemorySize = totalPhysicalMemorySize - freePhysicalMemorySize;
        double d = Math.pow(1024, 3);
        System.out.println(totalPhysicalMemorySize / d);
        System.out.println(freePhysicalMemorySize / d);
        System.out.println(usedPhysicalMemorySize / d);/*

        double processCpuLoad = mxbean.getProcessCpuLoad();
        System.out.println(processCpuLoad);
        long processCpuTime = mxbean.getProcessCpuTime();
        System.out.println(processCpuTime);*/
    }
}
