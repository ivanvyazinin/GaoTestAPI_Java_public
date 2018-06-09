package main.java.api.directories;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.*;

public class IscoAPI extends CommonAPI {

    public IscoAPI(){
        setURL(API_PREFIX, ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_ISCO);
    }

}
