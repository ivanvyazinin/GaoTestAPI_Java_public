package main.java.api.publications;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_MEDIA_PUBLICATIONS;

public class MediaPublicationsAPI extends CommonAPI {

    public MediaPublicationsAPI(){
        setURL(API_PREFIX, ENDPOINT_MEDIA_PUBLICATIONS);
    }



}
