package main.java.api.contentCloud;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_constructor_screens;

public class ConstructorScreensAPI extends CommonAPI {
    public ConstructorScreensAPI(){
        setURL(API_PREFIX, ENDPOINT_constructor_screens);
        resetRequestParameters();
    }
}
