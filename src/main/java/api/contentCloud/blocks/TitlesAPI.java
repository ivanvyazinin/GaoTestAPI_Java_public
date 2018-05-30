package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_TITLES;

public class TitlesAPI extends CommonAPI {

    public TitlesAPI(){
        setURL(API_PREFIX, ENDPOINT_TITLES);
    }
}
