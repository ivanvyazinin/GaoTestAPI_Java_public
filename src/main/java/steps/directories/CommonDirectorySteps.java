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

    @Step("Search 'search_request' into directory '{directory}'")
    public void searchIntoDirectory(String directory, String search_request){
        response = directoryCommonAPI.search(directory, search_request);
    }

    @Step("Retrieving Directory '{directory}'")
    public void getDirectoryLevelTwo(String directory){
        response = directoryCommonAPI.get(directory + "?level=2");
    }
}
