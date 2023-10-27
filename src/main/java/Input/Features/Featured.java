package Input.Features;

import API.APIAuthorization;
import Input.FeatureWrapper;
import Input.InputAnnotations.InputPrefix;
import http.HandleHttp;
import http.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;

@InputPrefix(name = "featured")
public class Featured extends FeatureWrapper {


    public Featured() {
        super(Featured.class);
    }

    @Override
    public LinkedList<String> getSetOutput() {
        LinkedList<String> featureList = new LinkedList<>();
        String formattedFeatureList = "";
        HandleHttp categoryRequest = new HandleHttp(String.format("%s/v1/browse/featured-playlists", APIAuthorization.API_PATH), 1);

        String json = Response.get().getResponse();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        String access_token = jo.get("access_token").getAsString();

        String jsonResponse = categoryRequest.GET(access_token).body();


        JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject categoriesObj = responseObject.getAsJsonObject("playlists");

        for (JsonElement element : categoriesObj.getAsJsonArray("items")) {
            formattedFeatureList = "";
            String name = element.getAsJsonObject().get("name").getAsString();
            String url = element.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString();
            formattedFeatureList += name + "\n" + url + "\n";
            featureList.add(formattedFeatureList);
        }

        return featureList;
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public void setInput(String input) {

    }


}
