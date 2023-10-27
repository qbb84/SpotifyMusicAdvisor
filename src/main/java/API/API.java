package API;


public class API implements ApiDefaults {

    private final String AUTH_URL;
    private boolean AUTH_SUCCESS;

    {
        AUTH_SUCCESS = false;
    }
    
    public API(String authURL) {
        this.AUTH_URL = authURL;
    }


    @Override
    public String getAuthURL() {
        return AUTH_URL;
    }

    @Override
    public boolean isAuthSuccess() {
        return AUTH_SUCCESS;
    }

    @Override
    public void setAuthSuccess(boolean isSuccess) {
        this.AUTH_SUCCESS = isSuccess;
    }


}
