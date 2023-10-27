package http;

import com.sun.net.httpserver.HttpServer;

import java.util.LinkedHashMap;

public class HttpServerManager {

    private static final LinkedHashMap<Integer, HttpServer> httpServerHashMap;


    static {
        httpServerHashMap = new LinkedHashMap<>();
    }

    public static void setActiveServers(int SocketAddress, HttpServer server) {
        getActiveServers().put(SocketAddress, server);
    }

    public static LinkedHashMap<Integer, HttpServer> getActiveServers() {
        return httpServerHashMap;
    }
}
