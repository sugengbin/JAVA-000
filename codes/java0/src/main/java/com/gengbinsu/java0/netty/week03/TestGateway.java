package com.gengbinsu.java0.netty.week03;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gengbinsu 2020年11月4日
 */
public class TestGateway {

	public static void main(String[] args) throws URISyntaxException {
		// start server1 8081:
		// com.gengbinsu.java0.netty.week03.backend.server1.NettyServerApplication
		
		// start server1 8082:
		// com.gengbinsu.java0.netty.week03.backend.server1.NettyServerApplication
		
		// start gateway 8888:
		// com.gengbinsu.java0.netty.week03.gateway.NettyServerApplication
		// RoundRobinRouter 简单实现
		// VersionHttpRequestFilter 简单demo: header添加version

		// test request 8888
		Map<String, String> params = new HashMap<>();
		params.put("name", "sugengbin");
		for (int i = 0; i < 10; i++) {
			Optional<String> result = MyHttpClient.doGet("http://localhost:8888/test", params);
			result.ifPresent(rtn -> System.out.println(rtn));
		}
		// result:
//		hello, sugengbin, this is server1,version:20201104
//		hello, sugengbin, this is server2,version:20201104
//		hello, sugengbin, this is server1,version:20201104
//		hello, sugengbin, this is server2,version:20201104
//		hello, sugengbin, this is server1,version:20201104
//		hello, sugengbin, this is server2,version:20201104
//		hello, sugengbin, this is server1,version:20201104
//		hello, sugengbin, this is server2,version:20201104
//		hello, sugengbin, this is server1,version:20201104
//		hello, sugengbin, this is server2,version:20201104
	}
}
