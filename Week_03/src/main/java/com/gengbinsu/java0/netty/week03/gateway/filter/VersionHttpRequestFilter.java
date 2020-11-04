package com.gengbinsu.java0.netty.week03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by gengbinsu 2020年11月4日
 */
public class VersionHttpRequestFilter implements HttpRequestFilter {

	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		fullRequest.headers().set("version", "20201104");
	}
}
