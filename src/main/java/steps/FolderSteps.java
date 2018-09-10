package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.FolderItemsAPI;

public class FolderSteps extends CommonSteps {
    public FolderItemsAPI folderItemsAPI = new FolderItemsAPI();

    @Step("Get items into folder '{folderId}'")
    public void getFolderItems(String folderId){
        if (folderId!=null){
            String [][] parameters = {
                    {"parentFolder",folderId}
            };
            folderItemsAPI.setRequestParameters(parameters);
        }
        response = folderItemsAPI.get();
    }
}
