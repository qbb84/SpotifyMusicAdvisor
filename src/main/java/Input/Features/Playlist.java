package Input.Features;

import API.APIAuthorization;
import Input.FeatureWrapper;
import Input.Features.PlaylistUtil.ConvertID;
import Input.InputAnnotations.InputPrefix;
import http.HandleHttp;
import http.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;

@InputPrefix(name = "playlist", arguments = "mood")
public class Playlist extends FeatureWrapper {


    private String input;

    public Playlist() {
        super(Playlist.class);
    }


    @Override
    public LinkedList<String> getSetOutput() {
        LinkedList<String> playlistList = new LinkedList<>();
        String formattedPlaylist = "";
        String playlistInput = new ConvertID().convert(getInput());

        HandleHttp categoryRequest = new HandleHttp(String.format("%s/v1/browse/categories/%s/playlists", APIAuthorization.API_PATH, playlistInput), 1);

        String json = Response.get().getResponse();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        String access_token = jo.get("access_token").getAsString();

        String jsonResponse = categoryRequest.GET(access_token).body();


        JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();


        if (responseObject.has("error")) {
            JsonObject categoriesObj = responseObject.getAsJsonObject("error");
            System.out.println(categoriesObj.get("message").getAsString());
            return null;
        }


        JsonObject categoriesObj = responseObject.getAsJsonObject("playlists");


        for (JsonElement element : categoriesObj.getAsJsonArray("items")) {
            formattedPlaylist = "";
            String name = element.getAsJsonObject().get("name").getAsString();
            String url = element.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString();
            formattedPlaylist += name + "\n" + url + "\n";
            playlistList.add(formattedPlaylist);
        }

        return playlistList;
    }


    @Override
    public String getInput() {
        return this.input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }
}
