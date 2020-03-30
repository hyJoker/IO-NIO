package com.example.demo.nio;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName NIOTest
 * @Description
 * @Author Rex
 * @Date 2020-03-30 10:10
 * @Version 1.0
 **/
public class NIOTest {

    public static void main(String[] args) {
        try {
            //NIOTest.copyFile();
            NIOTest.copyFileDirect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地IO: 使用间接缓冲区,复制文件
     */
    public static void copyFile() throws Exception {
        long start = System.currentTimeMillis();
        // 输入管道
        FileChannel inch = FileChannel.open(Paths.get("E:\\Chrome.exe"), StandardOpenOption.READ);
        // 输出管道
        FileChannel outch = FileChannel.open(Paths.get("E:\\ChromeBak.exe"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        // 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inch.read(buffer) != -1) {
            // 切换读取模式
            buffer.flip();
            outch.write(buffer);
            buffer.clear();
        }

        inch.close();
        outch.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 本地IO: 使用直接缓冲区,复制文件
     */
    public static void copyFileDirect() throws Exception {
        long start = System.currentTimeMillis();
        // 输入管道
        FileChannel inch = FileChannel.open(Paths.get("E:\\Chrome.exe"), StandardOpenOption.READ);
        // 输出管道
        FileChannel outch = FileChannel.open(Paths.get("E:\\ChromeBak.exe"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        MappedByteBuffer inmap = inch.map(FileChannel.MapMode.READ_ONLY, 0, inch.size());
        MappedByteBuffer outmap = outch.map(FileChannel.MapMode.READ_WRITE, 0, inch.size());

        // 缓冲区
        byte[] bts = new byte[inmap.limit()];
        inmap.get(bts);
        outmap.get(bts);

        inch.close();
        outch.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
