package http;

import API.APIAuthorization;
import MVC.PageController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class ServerContext {

    public static String output;
    public final HttpServer server;
    public String response;

    ServerContext(HttpServer server) {
        this.server = server;
    }

    public void createServerContext(String[] args) {
        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        output = exchange.getRequestURI().getQuery();
                        String feedback;

                        if (output == null) {
                            feedback = "Authorization code not found. Try again.";

                            exchange.sendResponseHeaders(200, feedback.length());
                            exchange.getResponseBody().write(feedback.getBytes());
                            exchange.getResponseBody().close();
                        }


                        if (output.contains("access_denied")) {
                            feedback = "Authorization code not found. Try again.";

                            exchange.sendResponseHeaders(200, feedback.length());
                            exchange.getResponseBody().write(feedback.getBytes());
                            exchange.getResponseBody().close();

                        } else {
                            System.out.println("waiting for code...");
                            feedback = "Got the code. Return back to your program.";


                            System.out.println("code received");


                            System.out.println("making http request for access_token...");
                            System.out.println("response:");


                            if (args.length == 6) {
                                HandleHttp spotify = new HandleHttp(args[1] + "/api/token", 30);
                                response = spotify.POST(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", APIAuthorization.getClientId(), APIAuthorization.CLIENT_SECRET)).body();
                                Response.get().setResponse(response);

                            } else {
                                HandleHttp spotify = new HandleHttp("https://accounts.spotify.com/api/token", 30);
                                response = spotify.POST(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", APIAuthorization.getClientId(), APIAuthorization.CLIENT_SECRET)).body();
                                Response.get().setResponse(response);


                            }
                            exchange.sendResponseHeaders(200, feedback.length());
                            exchange.getResponseBody().write(feedback.getBytes());
                            exchange.getResponseBody().close();


                            System.out.println("---SUCCESS---");

                            PageController.apiAuth.invoke().setAuthSuccess(true);

                            server.stop(0);


                        }


                    }


                }
        );
    }
}
