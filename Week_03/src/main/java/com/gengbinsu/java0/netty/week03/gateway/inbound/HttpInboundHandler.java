package com.gengbinsu.java0.netty.week03.gateway.inbound;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gengbinsu.java0.netty.week03.gateway.filter.HttpRequestFilter;
import com.gengbinsu.java0.netty.week03.gateway.filter.VersionHttpRequestFilter;
import com.gengbinsu.java0.netty.week03.gateway.outbound.httpclient4.HttpOutboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServers;
    private HttpOutboundHandler handler;
    private List<HttpRequestFilter> reqFilters = new ArrayList<>();
    
    public HttpInboundHandler(String proxyServer) {
        this.proxyServers = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServers);
        reqFilters.add(new VersionHttpRequestFilter());
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            doFilters(fullRequest, ctx);
            handler.handle(fullRequest, ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

	private void doFilters(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		for (HttpRequestFilter filter: reqFilters) {
			filter.filter(fullRequest, ctx);
		}
	}

}
