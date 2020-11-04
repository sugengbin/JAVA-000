package com.gengbinsu.java0.netty.week03.gateway.router.strategy;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.gengbinsu.java0.netty.week03.gateway.router.HttpEndpointRouter;

/**
 * Created by gengbinsu 2020年11月4日
 */
public class RoundRobinRouter implements HttpEndpointRouter {

	private static RoundRobinRouter instance = null;
	List<String> hostList = null;
	AtomicInteger atomicIndex = new AtomicInteger(0);
	protected RoundRobinRouter() {
	}

	public synchronized static RoundRobinRouter getInstance() {
		if (instance == null) {
			instance = new RoundRobinRouter();
		}
		return instance;
	}
	
	
	@Override
	public String route(List<String> endpoints) {
		if (Objects.isNull(hostList)) {
			hostList = endpoints;
		}
		return getNextServiceHost();
	}

	public synchronized String getNextServiceHost() {
		int size = hostList.size();
		int index = 0;
		int indexTail = atomicIndex.getAndIncrement() % size;
		index = indexTail < 0 ? indexTail + size : indexTail;
		return hostList.get(index);
	}
}
