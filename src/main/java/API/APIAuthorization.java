package API;

import Input.InputAnnotations.InputPrefix;

@InputPrefix(name = "auth")
public class APIAuthorization {

    private static final API api;
    public static String CLIENT_ID = "";
    public static String CLIENT_SECRET = "";
    public static String REDIRECT_URL = "http://localhost:8080";
    public static String COMMAND_LINE_ADDRESS = "https://accounts.spotify.com";
    public static String API_PATH = "https://api.spotify.com";
    private static String AUTH_URL = "";

    static {
        api = new API(AUTH_URL);
    }

    public String SPOTIFY_AUTH_URL = String.format("%s/authorize?client_id=%s&redirect_uri=%s&response_type=code", COMMAND_LINE_ADDRESS, CLIENT_ID, REDIRECT_URL);


    public APIAuthorization() {

    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static void setRedirectUrl(String redirectUrl) {
        REDIRECT_URL = redirectUrl;
    }

    public APIAuthorization setAuthUrl(String authUrl) {
        AUTH_URL = authUrl;
        return new APIAuthorization();
    }

    public API invoke() {
        return api;
    }

    public String getSpotifyAuthUrl() {
        return SPOTIFY_AUTH_URL;
    }

    public boolean requestAuth() {
        return api.isAuthSuccess();
    }

    public String getSetOutput() {
        return api.getAuthURL();
    }


}
