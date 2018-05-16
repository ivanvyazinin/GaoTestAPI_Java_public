package main.java.api.contentCloud;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_SUBTITLES;

public class SubtitlesAPI extends CommonAPI {

    public SubtitlesAPI(){
        setURL(API_PREFIX, ENDPOINT_SUBTITLES);
        //parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
    }
}
