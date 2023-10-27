package http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HandleHtppServer {


    private final HttpServer server;


    public HandleHtppServer() {
        try {
            this.server = HttpServer.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        server.start();
    }

    public void stop(int delay) {
        server.stop(delay);
    }


    public  ServerContext setSocketAddress(int address) {
        try {
            server.bind(new InetSocketAddress(address), 0);
            HttpServerManager.setActiveServers(address, server);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ServerContext(server);
    }

}
