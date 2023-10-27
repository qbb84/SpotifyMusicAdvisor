package http;

public class Response {


    private static Response response;


    private String httpResponse;


    private Response() {
    }

    public static Response get() {
        if (response == null) {
            response = new Response();
        }
        return response;
    }

    public void setResponse(String response) {
        this.httpResponse = response;
    }

    public String getResponse() {
        return this.httpResponse;
    }
}
