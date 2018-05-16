package main.java.api.contentCloud;

import io.restassured.response.Response;
import main.java.api.CommonAPI;

import static io.restassured.RestAssured.given;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Context.HEADERS;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_TAGS;

public class TagsAPI extends CommonAPI {

    public TagsAPI(){
        setURL(API_PREFIX, ENDPOINT_TAGS);
        //parameters = "?embed[]=" + EMBED_FOLDER + "&embed[]=" + EMBED_TAG + "&embed[]=" + EMBED_CONSTRUCTOR;
    }

    public <T> Response addTag(String resource, String resourceId, T bodyData) {
        return given().
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(bodyData).
                when().
                post(getURL() + "/" + resource + "/" + resourceId);
    }

    public Response deleteTag(String resource, String resourceId, String tagId) {
        return given().
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                delete(getURL() + "/" + resource + "/" + resourceId + "/" + tagId);
    }

}