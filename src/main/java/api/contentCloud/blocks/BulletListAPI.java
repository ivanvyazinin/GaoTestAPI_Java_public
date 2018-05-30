package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_BULLETLIST;

public class BulletListAPI extends CommonAPI {

    public BulletListAPI(){
        setURL(API_PREFIX, ENDPOINT_BULLETLIST);
    }
}
