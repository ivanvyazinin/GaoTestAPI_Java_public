package main.java.steps.directories;

import io.qameta.allure.Step;
import main.java.api.directories.DirectoryCommonAPI;
import main.java.steps.CommonSteps;

public class CommonDirectorySteps extends CommonSteps {

    DirectoryCommonAPI directoryCommonAPI = new DirectoryCommonAPI();

    @Step("Retrieving Directory '{directory}'")
    public void getDirectory(String directory){
        response = directoryCommonAPI.get(directory);
    }

}
