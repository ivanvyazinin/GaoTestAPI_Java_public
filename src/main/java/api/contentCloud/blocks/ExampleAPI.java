package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_EXAMPLE;

public class ExampleAPI extends CommonAPI {

    public ExampleAPI(){
        setURL(API_PREFIX, ENDPOINT_EXAMPLE);
    }
}
