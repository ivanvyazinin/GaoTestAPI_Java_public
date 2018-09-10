package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.FilesAPI;

import java.io.File;

public class FilesSteps {
    FilesAPI filesAPI = new FilesAPI();

    @Step("Upload file ")
    public String uploadFile(String filePath){
        return filesAPI.uploadFile(new File(filePath)).jsonPath().getString("data.file_id");
    }
}
