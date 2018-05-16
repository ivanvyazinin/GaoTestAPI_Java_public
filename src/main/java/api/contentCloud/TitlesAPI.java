package main.java.api.contentCloud;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_TITLES;

public class TitlesAPI extends CommonAPI {

    public TitlesAPI(){
        setURL(API_PREFIX, ENDPOINT_TITLES);
        //parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
    }
}
