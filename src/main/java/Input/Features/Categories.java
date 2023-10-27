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

@InputPrefix(name = "categories")
public class Categories extends FeatureWrapper {


    public Categories() {
        super(Categories.class);
    }

    @Override
    public LinkedList<String> getSetOutput() {
        LinkedList<String> responseList = new LinkedList<>();

        JsonObject responseObject = getAsJsonObject();

        JsonObject categoriesObj = responseObject.getAsJsonObject("categories");


        for (JsonElement element : categoriesObj.getAsJsonArray("items")) {
            responseList.add(element.getAsJsonObject().get("name").getAsString() + "\n");
        }


        return responseList;
    }

    private JsonObject getAsJsonObject() {
        HandleHttp categoryRequest = new HandleHttp(String.format("%s/v1/browse/categories", APIAuthorization.API_PATH), 1);

        String json = Response.get().getResponse();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        String access_token = jo.get("access_token").getAsString();

        String jsonResponse = categoryRequest.GET(access_token).body();
        JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        return responseObject;
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public void setInput(String input) {

    }


}
