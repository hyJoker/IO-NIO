package com.example.demo.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BlockingEchoServer
 * @Description
 * @Author Rex
 * @Date 2020-03-30 16:39
 * @Version 1.0
 **/
public class BlockingEchoServer {

    private static int DEFAULT_PORT = 65535;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
        try (
                Socket server = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        ) {
            while (true) {
                String inputLine = in.readLine();
                System.out.println("接收到:" + inputLine);
            }
        } catch (Exception e) {
            System.out.println("BlockingEchoServer异常!" + e.getMessage());
        }
    }

}
