package main.java.api.contentCloud.blocks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_BLOCKS;

public class BlocsAPI extends CommonAPI {
    public BlocsAPI(){
        setURL(API_PREFIX, ENDPOINT_BLOCKS);
    }

    public Response copy(String folderId, String blockId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                put(getURL() + blockId + "/folders/" + folderId + "/copy");
    }
}
