package API;

public interface ApiDefaults {

    String authURL = null;
    boolean authSuccess = false;


    String getAuthURL();

    boolean isAuthSuccess();

    void setAuthSuccess(boolean isSuccess);


}
