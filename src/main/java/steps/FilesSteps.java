package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.FilesAPI;
import main.java.core.Context;
import main.java.entities.contentCloud.Files;
import main.java.entities.contentCloud.blocks.multimedia.CropSettings;

import java.io.File;

public class FilesSteps extends CommonSteps{
    private FilesAPI filesAPI = new FilesAPI();

    @Step("Upload file '{filePath}'")
    public String uploadFile(String filePath){
        return filesAPI.uploadFile(new File(filePath)).jsonPath().getString("data.file_id");
    }

    public String  uploadFileWithLicense(String filePath, Context context){
        String fileId =  filesAPI.uploadFile(new File(filePath)).jsonPath().getString("data.file_id");
        filesAPI.put(fileId, new Files(context.getFileLicense().id));
        return fileId;
    }

    @Step("Crop file '{fileId}'")
    public void cropFile(CropSettings cropSettings, String fileId){
        response = filesAPI.cropFile(cropSettings, fileId);
    }

    @Step("Remove crop from file '{fileId}'")
    public void removeCrop(String fileId){
        response = filesAPI.removeCrop(fileId);
    }
}
