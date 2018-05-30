package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_TABLE;

public class TableAPI extends CommonAPI {

    public TableAPI(){
        setURL(API_PREFIX, ENDPOINT_TABLE);
    }
}
