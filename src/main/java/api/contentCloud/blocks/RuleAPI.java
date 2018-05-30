package main.java.api.contentCloud.blocks;

import main.java.api.CommonAPI;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_RULE;

public class RuleAPI extends CommonAPI {

    public RuleAPI(){
        setURL(API_PREFIX, ENDPOINT_RULE);
    }
}
