package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.*;

public class TheoryBlocksAPI extends CommonAPI {

    public TheoryBlocksAPI(String entity){
        setURL(API_PREFIX, ENDPOINT_THEORY_BLOCKS + entity);
    }
}
