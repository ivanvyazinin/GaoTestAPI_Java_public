package main.java.api.contentCloud;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_FOLDERS;

public class FoldersAPI extends CommonAPI {

    public FoldersAPI(){
        setURL(API_PREFIX, ENDPOINT_FOLDERS);
        resetRequestParameters();
    }

    public Response getFolderItems(String id) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + "/" + id + "/items" + getRequestParameters());
    }
}