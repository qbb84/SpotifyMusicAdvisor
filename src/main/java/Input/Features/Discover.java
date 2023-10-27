package Input.Features;

import API.APIAuthorization;
import Input.FeatureWrapper;
import Input.InputAnnotations.InputPrefix;
import http.HandleHttp;
import http.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;

@InputPrefix(name = "discover")
public class Discover extends FeatureWrapper {


    public Discover() {
        super(Discover.class);
    }

    @Override
    public LinkedList<String> getSetOutput() {
        LinkedList<String> responseList = new LinkedList<>();

        HandleHttp categoryRequest = new HandleHttp(String.format("%s/v1/browse/new-releases", APIAuthorization.API_PATH), 1);

        String json = Response.get().getResponse();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        String access_token = jo.get("access_token").getAsString();

        String jsonResponse = categoryRequest.GET(access_token).body();
        JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();


        JsonObject categoriesObj = responseObject.getAsJsonObject("albums");


        for (JsonElement element : categoriesObj.getAsJsonArray("items")) {
            String formattedOutput = "";

            JsonObject artists = JsonParser.parseString(String.valueOf(element.getAsJsonObject().get("artists").getAsJsonArray().asList().get(0))).getAsJsonObject();

            formattedOutput += "\n" + element.getAsJsonObject().get("name").getAsString() + "\n";
            formattedOutput += "[";

            JsonArray arr = element.getAsJsonObject().get("artists").getAsJsonArray();
            for (JsonElement element1 : arr) {
                if (arr.get(arr.size() - 1) != element1) {

                    formattedOutput += element1.getAsJsonObject().get("name").getAsString() + ",";
                } else {

                    formattedOutput += element1.getAsJsonObject().get("name").getAsString();
                }
            }
            formattedOutput += "]" + "\n";


            formattedOutput += element.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString() + "\n";
            responseList.add(formattedOutput);
        }
        return responseList;
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public void setInput(String input) {

    }


}
