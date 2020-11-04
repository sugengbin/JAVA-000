package com.gengbinsu.java0.netty.week02.server;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8808);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
