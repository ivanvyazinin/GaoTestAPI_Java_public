package main.java.api.contentCloud;

import main.java.api.CommonAPI;

import static main.java.properties.Constants.EMBED_CONSTRUCTOR;
import static main.java.properties.Constants.EMBED_FOLDER;
import static main.java.properties.Constants.EMBED_TAG;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_CONTENT_ITEMS;

public class ContentItemAPI extends CommonAPI {

    public ContentItemAPI(){
        setURL(API_PREFIX, ENDPOINT_CONTENT_ITEMS);
        parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
    }

}
