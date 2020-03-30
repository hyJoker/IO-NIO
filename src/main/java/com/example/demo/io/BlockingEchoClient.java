package com.example.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @ClassName BlockingEchoClient
 * @Description 标准IO实现实现客户端,服务端通信
 * @Author Rex
 * @Date 2020-03-30 17:11
 * @Version 1.0
 **/
public class BlockingEchoClient {
    private static String HOST_NAME = "127.0.0.1";
    private static int PORT = 65535;

    public static void main(String[] args) {
        try (
                Socket client = new Socket(HOST_NAME, PORT);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            while (true) {
                String userInput = stdIn.readLine();
                System.out.println("输入:" + userInput);
                out.write(userInput);
                // 通知服务端,数据发送完成
                out.write("\n");
                out.flush();
                System.out.println("echo: " + userInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
