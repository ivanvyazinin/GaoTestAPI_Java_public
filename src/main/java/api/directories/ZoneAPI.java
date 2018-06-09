package main.java.api.directories;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY_ZONES;

public class ZoneAPI extends CommonAPI {

    public ZoneAPI(){
        setURL(API_PREFIX, ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_ZONES);
    }
}
