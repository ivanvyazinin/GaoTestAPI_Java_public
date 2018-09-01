package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS;

public class CommonBlocsAPI extends CommonAPI {
    public CommonBlocsAPI(String type, String entity){
        setURL(API_PREFIX, ENDPOINT_BLOCKS + type + entity);
    }

    public CommonBlocsAPI(String entity){

        setURL(API_PREFIX, ENDPOINT_BLOCKS + entity);
    }

}
