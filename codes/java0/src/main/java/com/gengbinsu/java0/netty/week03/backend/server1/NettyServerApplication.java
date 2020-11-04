package com.gengbinsu.java0.netty.week03.backend.server1;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8081);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
