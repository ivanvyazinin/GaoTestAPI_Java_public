package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_PARAGRAPH;

public class ParagraphAPI extends CommonAPI {

    public ParagraphAPI(){
        setURL(API_PREFIX, ENDPOINT_PARAGRAPH);
    }
}
