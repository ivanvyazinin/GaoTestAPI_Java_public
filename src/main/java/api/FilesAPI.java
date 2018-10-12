package main.java.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import main.java.entities.contentCloud.blocks.multimedia.CropSettings;

import java.io.File;

import static io.restassured.RestAssured.given;
import static main.java.core.Context.*;
import static main.java.properties.Constants.CONTENT_TYPE;
import static main.java.properties.Endpoints.*;

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
                .post(getURL() + ENDPOINT_FILES_UPLOAD);
    }

    public Response cropFile(CropSettings cropSettings, String fileId){

        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                body(cropSettings).
                when().
                post(getURL() + "/" + fileId + ENDPOINT_FILES_CROP);
    }

    public Response removeCrop(String fileId) {
        return given().
                filter(new AllureRestAssured()).
                contentType(CONTENT_TYPE).
                headers(HEADERS).
                when().
                delete(getURL() + "/" + fileId + ENDPOINT_FILES_CROP_REMOVE);
    }
}