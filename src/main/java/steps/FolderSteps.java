package main.java.steps;

import io.qameta.allure.Step;
import main.java.api.contentCloud.FolderItemsAPI;
import main.java.api.contentCloud.FoldersAPI;
import main.java.entities.contentCloud.folderItems.Folder;

import static main.java.properties.Constants.PATH_ID;

public class FolderSteps extends CommonSteps {
    public Folder testFolder;
    public FoldersAPI api = new FoldersAPI();
    public FolderItemsAPI folderItemsAPI = new FolderItemsAPI();

    @Step("Creating folder with random name into folder '{parentFolder}'")
    public void createFolder(String parentFolder){
        testFolder = new Folder(parentFolder);
        response = api.post(testFolder);
        testFolder.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating folder with random name into folder '{parentFolder}'")
    public Folder createFolder(Folder folder){
        response = api.post(folder);
        folder.id = response.jsonPath().getString(PATH_ID);
        return folder;
    }

    @Step("Get all folders")
    public void getAllFolder(){
        response = api.get();
    }

    @Step("Get items into folder '{folderId}'")
    public void getFolderItems(String folderId){
        response = api.getFolderItems(folderId);
    }

    @Step("Search items")
    public void searchFolderItems(){
        response = folderItemsAPI.get();
    }

}
