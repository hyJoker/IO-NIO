package com.example.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName IOTest
 * @Description io测试类
 * @Author Rex
 * @Date 2020-03-30 09:48
 * @Version 1.0
 **/
public class IOTest {

    public static void main(String[] args) {
        IOTest.copyFile();
    }

    /**
     * 使用IO流copy文件
     */
    public static void copyFile() {
        long start = System.currentTimeMillis();
        try (
                FileInputStream fis = new FileInputStream("E:\\Chrome.exe");
                FileOutputStream fos = new FileOutputStream("E:\\ChromeBak.exe")
        ) {
            byte[] bt = new byte[1024];
            while (fis.read(bt) > 0) {
                fos.write(bt);
            }
        } catch (Exception e) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
