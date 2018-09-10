package main.java.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static main.java.properties.Context.*;
import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_FILES;

public class FilesAPI extends CommonAPI{

    public FilesAPI(){
        setURL(API_PREFIX, ENDPOINT_FILES);
    }

    public Response uploadFile(File file){

        return given()
                .filter(new AllureRestAssured())
                .headers(HEADERS)
                .multiPart("file", file)
                .when()
                .post(getURL());
    }
}