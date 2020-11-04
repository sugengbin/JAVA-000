package com.gengbinsu.java0.netty.week03.backend.server2;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8082);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
