package main.java.api.directories;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY;

public class DirectoryCommonAPI extends CommonAPI {

    public DirectoryCommonAPI(){
            setURL(API_PREFIX, ENDPOINT_DIRECTORY);
    }

    public Response get(String entity) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + entity);
    }

    public Response search(String entity, String search_request) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                get(getURL() + entity + "?search=" + search_request);
    }
}
