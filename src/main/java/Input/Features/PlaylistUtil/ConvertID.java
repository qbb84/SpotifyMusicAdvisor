package Input.Features.PlaylistUtil;

import API.APIAuthorization;
import http.HandleHttp;
import http.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public record ConvertID() {

    public String convert(String input) {
        HashMap<String, String> getIDFromString = new LinkedHashMap<>();

        HandleHttp categoryRequest = new HandleHttp(String.format("%s/v1/browse/categories", APIAuthorization.API_PATH), 1);

        String json = Response.get().getResponse();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        String access_token = jo.get("access_token").getAsString();

        String jsonResponse = categoryRequest.GET(access_token).body();
        JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject category = responseObject.getAsJsonObject("categories");

        for (JsonElement element : category.getAsJsonArray("items")) {
            getIDFromString.put(element.getAsJsonObject().get("name").getAsString(), element.getAsJsonObject().get("id").getAsString());

        }

        for (Map.Entry<String, String> elements : getIDFromString.entrySet()) {
            if (elements.getKey().trim().equalsIgnoreCase(input.trim())) {
                return elements.getValue();
            }
        }
        return null;

    }
}
