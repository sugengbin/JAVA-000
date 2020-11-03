package com.gengbinsu.java0.nio;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * -Xmx512m
 * Created by gengbinsu 2020年11月3日
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      λ sb -u https://localhost:8801/ -c 40 -N 30
//    	Starting at 2020/11/3 15:58:41
//    	[Press C to stop the test]
//    	687     (RPS: 14.6)
//    	---------------Finished!----------------
//    	Finished at 2020/11/3 15:59:28 (took 00:00:47.1459726)
//    	720     (RPS: 15.3)                     Status 303:    720
//
//    	RPS: 22.6 (requests/second)
//    	Max: 2638ms
//    	Min: 1661ms
//    	Avg: 1721.5ms
//
//    	  50%   below 1692ms
//    	  60%   below 1694ms
//    	  70%   below 1697ms
//    	  80%   below 1702ms
//    	  90%   below 1720ms
//    	  95%   below 1911ms
//    	  98%   below 2362ms
//    	  99%   below 2497ms
//    	99.9%   below 2638ms
        
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