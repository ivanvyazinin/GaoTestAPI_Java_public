package main.java.api.publications;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_PUBLICATIONS;

public class PublicationsAPI extends CommonAPI {

    public PublicationsAPI(){
        setURL(API_PREFIX, ENDPOINT_PUBLICATIONS);
    }



}
