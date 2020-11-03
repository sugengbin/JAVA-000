package com.gengbinsu.java0.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by gengbinsu 2020年11月3日
 */
public class HttpServer03 {
    public static void main(String[] args) throws IOException{
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        final ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));// 对比server2, 使用线程池，固定线程数40, rps提升一倍
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      λ sb -u https://localhost:8803/ -c 40 -N 30
//    	Starting at 2020/11/3 16:18:57
//    	[Press C to stop the test]
//    	2262    (RPS: 64.7)
//    	2281    (RPS: 64.7)                     ---------------Finished!----------------
//    	2291    (RPS: 64.9)                     Finished at 2020/11/3 16:19:32 (took 00:00:35.4013529)
//    	Status 303:    2291
//
//    	RPS: 72.8 (requests/second)
//    	Max: 823ms
//    	Min: 44ms
//    	Avg: 285.2ms
//
//    	  50%   below 290ms
//    	  60%   below 312ms
//    	  70%   below 341ms
//    	  80%   below 372ms
//    	  90%   below 407ms
//    	  95%   below 442ms
//    	  98%   below 497ms
//    	  99%   below 556ms
//    	99.9%   below 685ms
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

