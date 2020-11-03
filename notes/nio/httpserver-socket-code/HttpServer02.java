package com.gengbinsu.java0.nio;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * -Xmx512m
 * Created by gengbinsu 2020年11月3日
 */
public class HttpServer02 {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {// 对比server01，使用线程处理，提高并发性能，RPS提高一倍
                    service(socket);
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      λ sb -u https://localhost:8802/ -c 40 -N 30
//    	Starting at 2020/11/3 16:11:20
//    	[Press C to stop the test]
//    	1350    (RPS: 35)
//    	1365    (RPS: 35.1)                     ---------------Finished!----------------
//    	1371    (RPS: 35.2)                     Finished at 2020/11/3 16:11:59 (took 00:00:39.2035891)
//    	Status 303:    1376
//
//    	RPS: 42.8 (requests/second)
//    	Max: 1137ms
//    	Min: 46ms
//    	Avg: 378.5ms
//
//    	  50%   below 343ms
//    	  60%   below 417ms
//    	  70%   below 479ms
//    	  80%   below 537ms
//    	  90%   below 626ms
//    	  95%   below 715ms
//    	  98%   below 828ms
//    	  99%   below 964ms
//    	99.9%   below 1085ms
    }
    
    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}