package main.java.api.contentCloud;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_FOLDER_ITEMS;

public class FolderItemsAPI extends CommonAPI {

    public FolderItemsAPI(){
        setURL(API_PREFIX, ENDPOINT_FOLDER_ITEMS);
        resetRequestParameters();
    }
}