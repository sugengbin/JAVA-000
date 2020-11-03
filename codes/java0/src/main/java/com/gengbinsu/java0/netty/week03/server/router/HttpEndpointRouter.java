package com.gengbinsu.java0.netty.week03.server.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
}
