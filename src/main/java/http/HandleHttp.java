package http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HandleHttp {


    private static final HttpClient httpClient;

    static {
        httpClient = HttpClient.newHttpClient();
    }

    private String URI = "";
    private int timeoutTime = 0;


    public HandleHttp(String URI, int timeoutTime) {
        this.URI = URI;
        this.timeoutTime = timeoutTime;
    }


    public HttpResponse<String> GET(String accessToken) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .uri(java.net.URI.create(URI))
                .GET()
                .timeout(Duration.ofSeconds(getTimeoutTime()))
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " OOPS!");
        }

        return response;
    }

    public int getTimeoutTime() {
        return timeoutTime;
    }

    public void setTimeoutTime(int timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

    public HttpResponse<String> POST(String messageToPost) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(java.net.URI.create(getURI()))
                .POST(HttpRequest.BodyPublishers.ofString(messageToPost))
                .timeout(Duration.ofMinutes(getTimeoutTime()))
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " OOPS!");
        }

        return response;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }
}
